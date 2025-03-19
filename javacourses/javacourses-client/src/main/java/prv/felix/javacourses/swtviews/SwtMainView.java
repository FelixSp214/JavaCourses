package prv.felix.javacourses.swtviews;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.CourseType;
import prv.felix.javacourses.enums.DBState;
import prv.felix.javacourses.interfaces.IClient;

import java.util.*;
import java.util.List;
import java.util.function.Function;

public class SwtMainView implements IClient {

	private Controller controller;
	private static boolean[] ascending;

	private Display display;
	private Text idText;
	private Text descriptionText;
	private Text participantsText;
	private Combo difficultyBox;
	private Text nameText;
	private Text lengthText;
	private Text costText;
	private Table table;

	public SwtMainView(Controller controller) {
		this.controller = controller;
		display = new Display();
		ascending = new boolean[Columns_JavaCourses.values().length];
		Arrays.fill(ascending, true);
	}

	@Override
	public void show() {
		Shell mainShell = new Shell(display);
		mainShell.setText("Java-Kurs-Verwaltung");
		createView(mainShell);
		mainShell.open ();
		while (!mainShell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

	private void createView(Shell shell) {
		GridLayout mainLayout = new GridLayout();
		mainLayout.numColumns = 2;
		shell.setLayout(mainLayout);
		Image java = new Image(display, SwtMainView.class.getClassLoader().getResourceAsStream("logo/java_logo.png"));
		shell.setImage(java);

		GridData logoData = new GridData();
		logoData.horizontalAlignment = SWT.CENTER;
		logoData.verticalAlignment = SWT.CENTER;
		Label logoLabel = new Label(shell, SWT.NONE);
		Image logo = new Image(display, SwtMainView.class.getClassLoader().getResourceAsStream("logo/verwaltung_logo.png"));
		ImageData imageData = logo.getImageData().scaledTo(200, 200);
		Image scaledImage = new Image(display, imageData);
		logoLabel.setImage(scaledImage);
		logoLabel.setLayoutData(logoData);

		createFilterField(shell);
		createButtonField(shell);
		createTableField(shell);

		try {
			//fillTable(controller.getAllJavaCourses());
			fillTable(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void fillTable(List<JavaCourse> courses) {
		//nur fürs testen
		courses = new ArrayList<>();
		courses.add(new JavaCourse(UUID.randomUUID(), "TestName", "TestDesc", 1, 2, 3, CourseType.TASTER_COURSE, DBState.ACTIVE));
		courses.add(new JavaCourse(UUID.randomUUID(), "a", "TestDesc", 1, 2, 3, CourseType.TASTER_COURSE, DBState.ACTIVE));
		courses.add(new JavaCourse(UUID.randomUUID(), "z", "TestDesc", 1, 2, 3, CourseType.TASTER_COURSE, DBState.ACTIVE));
		for(JavaCourse course : courses) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[]{
					course.getUuid().toString(),
					course.getCourseName(),
					course.getDescription(),
					String.valueOf(course.getDurationInHours()),
					String.valueOf(course.getMaxParticipants()),
					String.valueOf(course.getCostInEuros()),
					course.getCourseTyp().coursesType,
					course.getDbState().dbstate
			});
		}
	}

	private void createFilterField(Shell shell) {
		Composite filterComposite = new Composite(shell, SWT.NONE);
		GridLayout filterLayout = new GridLayout();
		filterLayout.numColumns = 4;
		filterLayout.makeColumnsEqualWidth = true;
		filterComposite.setLayout(filterLayout);
		GridData filterData = new GridData();
		filterData.grabExcessHorizontalSpace = true;
		filterData.horizontalAlignment = SWT.FILL;
		filterComposite.setLayoutData(filterData);

		createFirstRowFilter(filterComposite);
		createSecondRowFilter(filterComposite);
	}

	private void createFirstRowFilter(Composite filterComposite) {
		Label idLabel = new Label(filterComposite, SWT.NONE);
		idLabel.setText("ID");
		Label descriptionLabel = new Label(filterComposite, SWT.NONE);
		descriptionLabel.setText("Beschreibung");
		Label participantsLabel = new Label(filterComposite, SWT.NONE);
		participantsLabel.setText("Max. Teilnehmer");
		Label difficultyLabel = new Label(filterComposite, SWT.NONE);
		difficultyLabel.setText("Schwierigkeit");

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.FILL;
		idText = new Text(filterComposite, SWT.NONE);
		idText.setLayoutData(textData);
		descriptionText = new Text(filterComposite, SWT.NONE);
		descriptionText.setLayoutData(textData);
		participantsText = new Text(filterComposite, SWT.NONE);
		participantsText.setLayoutData(textData);
		GridData comboData = new GridData();
		comboData.grabExcessHorizontalSpace = true;
		comboData.horizontalAlignment = SWT.FILL;
		difficultyBox = new Combo(filterComposite, SWT.READ_ONLY);
		for(CourseType type : CourseType.values()) {
			difficultyBox.add(type.coursesType, type.ordinal());
		}
		difficultyBox.setLayoutData(comboData);
	}

	private void createSecondRowFilter(Composite filterComposite) {
		Label nameLabel = new Label(filterComposite, SWT.NONE);
		nameLabel.setText("Name");
		Label lengthLabel = new Label(filterComposite, SWT.NONE);
		lengthLabel.setText("Länge (in h)");
		Label costLabel = new Label(filterComposite, SWT.NONE);
		costLabel.setText("Kosten (in €)");
		Label emptyLabel = new Label(filterComposite, SWT.NONE);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.FILL;
		nameText = new Text(filterComposite, SWT.NONE);
		nameText.setLayoutData(textData);
		lengthText = new Text(filterComposite, SWT.NONE);
		lengthText.setLayoutData(textData);
		costText = new Text(filterComposite, SWT.NONE);
		costText.setLayoutData(textData);
		GridData buttonData = new GridData();
		buttonData.grabExcessHorizontalSpace = true;
		buttonData.horizontalAlignment = SWT.FILL;
		Button filterButton = new Button(filterComposite, SWT.READ_ONLY);
		filterButton.setLayoutData(buttonData);
		filterButton.setText("Filtern");
	}

	private void createButtonField(Shell shell) {
		Composite buttonComposite = new Composite(shell, SWT.NONE);
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.numColumns = 1;
		GridData compositeData = new GridData();
		compositeData.horizontalAlignment = SWT.FILL;
		compositeData.verticalAlignment = SWT.BEGINNING;
		compositeData.widthHint = 200;
		buttonComposite.setLayout(buttonLayout);
		buttonComposite.setLayoutData(compositeData);

		GridData buttonData = new GridData();
		buttonData.grabExcessHorizontalSpace = true;
		buttonData.horizontalAlignment = SWT.FILL;
		Button deleteButton = new Button(buttonComposite, SWT.NONE);
		deleteButton.setText("Löschen");
		deleteButton.setLayoutData(buttonData);
		Button newButton = new Button(buttonComposite, SWT.NONE);
		newButton.setText("Neu");
		newButton.setLayoutData(buttonData);
		Button importButton = new Button(buttonComposite, SWT.NONE);
		importButton.setText("Importieren");
		importButton.setLayoutData(buttonData);
		Button exportButton = new Button(buttonComposite, SWT.NONE);
		exportButton.setText("Exportieren");
		exportButton.setLayoutData(buttonData);
		logicForButtons(deleteButton, newButton, importButton, exportButton, shell);
	}

	private void logicForButtons(Button deleteButton, Button newButton, Button importButton, Button exportButton, Shell shell) {
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelection() != null && table.getSelection().length > 0) {
					MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
					messageBox.setText("Java-Kurse löschen");
					messageBox.setMessage("Sind Sie sicher, dass Sie " + table.getSelection().length + " Java-Kurs/-e löschen möchten?");
					if(messageBox.open() == SWT.YES) {
						for(TableItem item : table.getSelection()) {
							JavaCourse course = new JavaCourse();
							course.setUuid(UUID.fromString(item.getText(0)));
							course.setCourseName(item.getText(1));
							course.setDescription(item.getText(2));
							course.setDurationInHours(Integer.parseInt(item.getText(3)));
							course.setMaxParticipants(Integer.parseInt(item.getText(4)));
							course.setCostInEuros(Double.parseDouble(item.getText(5)));
							course.setCourseTyp(CourseType.getCourseType(item.getText(6)));
							course.setDbState(DBState.getDBState(item.getText(7)));
							try {
								controller.deleteJavaCourse(course);
							} catch (Exception ex) {
								MessageBox errorBox = new MessageBox(shell, SWT.OK);
								errorBox.setText("Fehler beim Kurs löschen!");
								errorBox.setMessage("Der Java-Kurs " + course.getCourseName() + " konnte nicht gelöscht werden!");
								errorBox.open();
							}
						}
					}
				}
			}
		});

		newButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SwtDetailView detailView = new SwtDetailView(display, controller);
				detailView.show();
			}
		});

		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SWTImportExportView importView = new SWTImportExportView(controller, display, true);
				importView.show();
			}
		});

		exportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SWTImportExportView exportView = new SWTImportExportView(controller, display, false);
				exportView.show();
			}
		});
	}

	private void createTableField(Shell shell) {
		GridData tableData = new GridData();
		tableData.grabExcessHorizontalSpace = true;
		tableData.grabExcessVerticalSpace = true;
		tableData.horizontalAlignment = SWT.FILL;
		tableData.verticalAlignment = SWT.FILL;
		table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(tableData);
		for(Columns_JavaCourses columnEnum : Columns_JavaCourses.values()) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(columnEnum.columnName);
			column.setWidth(columnEnum.columnWidth);
			column.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					sortTable(table, columnEnum.ordinal());
					ascending[columnEnum.ordinal()] = !ascending[columnEnum.ordinal()];
				}
			});
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Point point = new Point(e.x, e.y);
				TableItem item = table.getItem(point);
				if (item != null) {
					JavaCourse course = new JavaCourse();
					course.setUuid(UUID.fromString(item.getText(0)));
					course.setCourseName(item.getText(1));
					course.setDescription(item.getText(2));
					course.setDurationInHours(Integer.parseInt(item.getText(3)));
					course.setMaxParticipants(Integer.parseInt(item.getText(4)));
					course.setCostInEuros(Double.parseDouble(item.getText(5)));
					course.setCourseTyp(CourseType.getCourseType(item.getText(6)));
					course.setDbState(DBState.getDBState(item.getText(7)));
					SwtDetailView detailView = new SwtDetailView(display, controller, course);
					detailView.show();
				}
			}
		});
	}

	private static void sortTable(Table table, int columnIndex) {
		TableItem[] items = table.getItems();
		Arrays.sort(items, Comparator.comparing((Function<? super TableItem, ? extends String>) item -> item.getText(columnIndex),
				ascending[columnIndex] ? Comparator.naturalOrder() : Comparator.reverseOrder()));
		for (TableItem item : items) {
			new TableItem(table, SWT.NONE).setText(new String[]{
					item.getText(0), item.getText(1), item.getText(2), item.getText(3),
					item.getText(4), item.getText(5), item.getText(6), item.getText(7)
			});
			item.dispose();
		}
	}

}

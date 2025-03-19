package prv.felix.javacourses.swtviews;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.CourseType;
import prv.felix.javacourses.enums.DBState;
import prv.felix.javacourses.interfaces.IClient;

import java.util.UUID;

public class SwtDetailView implements IClient {

	private String headerText;
	private JavaCourse javaCourse;
	private boolean isNew;
	private Controller controller;

	private Display display;
	private Text idText;
	private Text nameText;
	private Text descriptionText;
	private Text durationText;
	private Text maxParticipantsText;
	private Text costText;
	private Combo difficultyCombo;

	public SwtDetailView(Display display, Controller controller, JavaCourse javaCourse) {
		this.display = display;
		this.javaCourse = javaCourse;
		this.controller = controller;
		this.headerText = "Java-Kurs: " + javaCourse.getCourseName();
		this.isNew = false;
	}

	public SwtDetailView(Display display, Controller controller) {
		this.display = display;
		this.javaCourse = new JavaCourse();
		this.javaCourse.setUuid(UUID.randomUUID());
		this.controller = controller;
		this.headerText = "Neuer Java-Kurs";
		this.isNew = true;
	}

	@Override
	public void show() {
		Shell detailShell = new Shell(display, SWT.MIN);
		detailShell.setText(headerText);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		detailShell.setLayout(layout);
		detailShell.setSize(550,800);
		Image java = new Image(display, SwtMainView.class.getClassLoader().getResourceAsStream("logo/java_logo.png"));
		detailShell.setImage(java);

		createDetailView(detailShell);

		detailShell.open();
	}

	private void createDetailView(Shell shell) {
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.FILL;

		Label idLabel = new Label(shell, SWT.NONE);
		idLabel.setText("ID");
		idText = new Text(shell, SWT.READ_ONLY | SWT.BORDER);
		idText.setText(javaCourse.getUuid().toString());
		idText.setLayoutData(textData);

		Label nameLabel = new Label(shell, SWT.NONE);
		nameLabel.setText("Name");
		nameText = new Text(shell, SWT.NONE);
		nameText.setLayoutData(textData);

		Label descriptionLabel = new Label(shell, SWT.NONE);
		descriptionLabel.setText("Beschreibung");
		GridData descData = new GridData();
		descData.grabExcessHorizontalSpace = true;
		descData.horizontalAlignment = SWT.FILL;
		descData.grabExcessVerticalSpace = true;
		descData.verticalAlignment = SWT.FILL;
		descriptionText = new Text(shell, SWT.V_SCROLL);
		descriptionText.setLayoutData(descData);

		Label durationLabel = new Label(shell, SWT.NONE);
		durationLabel.setText("Länge");
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 2;
		Composite durationComposite = new Composite(shell, SWT.NONE);
		durationComposite.setLayout(compositeLayout);
		durationComposite.setLayoutData(textData);
		durationText = new Text(durationComposite, SWT.NONE);
		durationText.setLayoutData(textData);
		Label hourLabel = new Label(durationComposite, SWT.NONE);
		hourLabel.setText(" h ");

		Label maxParticipantsLabel = new Label(shell, SWT.NONE);
		maxParticipantsLabel.setText("Max. Teilnehmer");
		maxParticipantsText = new Text(shell, SWT.NONE);
		maxParticipantsText.setLayoutData(textData);

		Label costLabel = new Label(shell, SWT.NONE);
		costLabel.setText("Kosten");
		Composite costComposite = new Composite(shell, SWT.NONE);
		costComposite.setLayout(compositeLayout);
		costComposite.setLayoutData(textData);
		costText = new Text(costComposite, SWT.NONE);
		costText.setLayoutData(textData);
		Label euroLabel = new Label(costComposite, SWT.NONE);
		euroLabel.setText(" € ");

		Label difficultyLabel = new Label(shell, SWT.NONE);
		difficultyLabel.setText("Schwierigkeit");
		difficultyCombo = new Combo(shell, SWT.READ_ONLY);
		for(CourseType type : CourseType.values()) {
			difficultyCombo.add(type.coursesType, type.ordinal());
		}
		difficultyCombo.setLayoutData(textData);

		GridData buttonData = new GridData();
		buttonData.grabExcessHorizontalSpace = true;
		buttonData.horizontalAlignment = SWT.END;
		Button saveButton = new Button(shell, SWT.NONE);
		saveButton.setText("Speichern");
		saveButton.setLayoutData(buttonData);
		logicForSaveButton(saveButton);

		if(!isNew) {
			fillTextFields();
		}
	}

	private void logicForSaveButton(Button saveButton) {
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!nameText.getText().isEmpty() &&
					!descriptionText.getText().isEmpty() &&
					!durationText.getText().isEmpty() &&
					!maxParticipantsText.getText().isEmpty() &&
					!costText.getText().isEmpty() &&
					!difficultyCombo.getText().isEmpty()) {
					javaCourse.setCourseName(nameText.getText());
					javaCourse.setDescription(descriptionText.getText());
					javaCourse.setDurationInHours(Integer.parseInt(durationText.getText()));
					javaCourse.setMaxParticipants(Integer.parseInt(maxParticipantsText.getText()));
					javaCourse.setCostInEuros(Double.parseDouble(costText.getText()));
					javaCourse.setCourseTyp(CourseType.getCourseType(difficultyCombo.getText()));
					javaCourse.setDbState(DBState.ACTIVE);
					try {
						if(isNew) {
							controller.createJavaCourse(javaCourse);
                    	} else {
							controller.updateJavaCourse(javaCourse);
						}
						nameText.getShell().dispose();
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				} else {
					MessageBox messageBox = new MessageBox(nameText.getShell(), SWT.OK);
					messageBox.setText("Warnung!");
					messageBox.setMessage("Alle Felder müssen ausgefüllt sein.");
					messageBox.open();
				}
			}
		});
	}

	private void fillTextFields() {
		nameText.setText(javaCourse.getCourseName());
		descriptionText.setText(javaCourse.getDescription());
		durationText.setText(String.valueOf(javaCourse.getDurationInHours()));
		maxParticipantsText.setText(String.valueOf(javaCourse.getMaxParticipants()));
		costText.setText(String.valueOf(javaCourse.getCostInEuros()));
		difficultyCombo.setText(javaCourse.getCourseTyp().coursesType);
	}

}

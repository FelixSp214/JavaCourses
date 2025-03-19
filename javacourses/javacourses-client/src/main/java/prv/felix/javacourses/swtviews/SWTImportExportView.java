package prv.felix.javacourses.swtviews;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.enums.ExportType;
import prv.felix.javacourses.interfaces.IClient;

public class SWTImportExportView implements IClient {

    private Controller controller;
    private boolean isImport;

    private Display display;
    private Text pathText;
    private Combo exportAsCombo;

    public SWTImportExportView(Controller controller, Display display, boolean isImport) {
        this.controller = controller;
        this.display = display;
        this.isImport = isImport;
    }

    @Override
    public void show() {
        Shell shell = new Shell(display, SWT.MIN);
        shell.setText(isImport ? "Java-Kurse Importieren" : "Java-Kurse Exportieren");
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        shell.setLayout(layout);
        shell.setSize(880,280);
        Image java = new Image(display, SwtMainView.class.getClassLoader().getResourceAsStream("logo/java_logo.png"));
        shell.setImage(java);

        createImportExportView(shell);

        shell.open();
    }

    private void createImportExportView(Shell shell) {
        GridData textData = new GridData();
        textData.grabExcessHorizontalSpace = true;
        textData.horizontalAlignment = SWT.FILL;

        Label pathLabel = new Label(shell, SWT.NONE);
        pathLabel.setText("Dateipfad");
        GridLayout pathLayout = new GridLayout();
        pathLayout.numColumns = 2;
        Composite pathComposite = new Composite(shell, SWT.NONE);
        pathComposite.setLayout(pathLayout);
        pathComposite.setLayoutData(textData);
        pathText = new Text(pathComposite, SWT.NONE);
        pathText.setLayoutData(textData);
        Button searchButton = new Button(pathComposite, SWT.NONE);
        searchButton.setText("Durchsuchen");

        Label exportAsLabel = new Label(shell, SWT.NONE);
        exportAsLabel.setText(isImport ? "Importieren als..." : "Exportieren als...");
        exportAsCombo = new Combo(shell, SWT.READ_ONLY);
        exportAsCombo.setLayoutData(textData);
        for(ExportType type : ExportType.values()) {
            exportAsCombo.add(type.exportType, type.ordinal());
        }

        GridData buttonData = new GridData();
        buttonData.grabExcessHorizontalSpace = true;
        buttonData.horizontalAlignment = SWT.END;
        Button exportButton = new Button(shell, SWT.NONE);
        exportButton.setText(isImport ? "Importieren" : "Exportieren");
        exportButton.setLayoutData(buttonData);

        logicForButtons(searchButton, exportButton);
    }

    private void logicForButtons(Button searchButton, Button exportButton) {
        searchButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String output;
                if(isImport) {
                    FileDialog fdlg = new FileDialog(searchButton.getShell());
                    output = fdlg.open();
                } else {
                    DirectoryDialog ddlg = new DirectoryDialog(searchButton.getShell());
                    output = ddlg.open();
                }

                if(output != null && !output.isEmpty()){
                    pathText.setText(output);
                }
            }
        });
    }

}

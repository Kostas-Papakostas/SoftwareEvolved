package gui.mainEngine;

//try to extract relationship beetween gui and pplSchema and pplTransition

import data.configurators.*;
import data.dataKeeper.GlobalDataKeeper;
import data.dataPPL.pplSQLSchema.PPLSchema;
import gui.actionListeners.*;
import gui.dialogs.EnlargeTable;
import gui.tableComputations.DetailedTableGraphicComputation;
import gui.tableComputations.GeneralTableGraphicComputation;
import gui.tableComputations.ZoomTableGraphicComputation;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.*;
import gui.tableElements.tableRenderers.IDUTableRenderer;
import gui.treeElements.TreeConstructionGeneral;
import gui.treeElements.TreeConstructionPhases;
import gui.treeElements.TreeConstructionPhasesWithClusters;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;
import tableClustering.clusterExtractor.engine.TableClusteringMainEngine;
import tableClustering.clusterValidator.engine.ClusterValidatorMainEngine;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* Refactor! Problem: Large Class, Long Method, Switch Statements, Comments, Duplicated Code */
public class Gui extends JFrame implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel lifeTimePanel = new JPanel();

    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    private MyTableModel generalModel = null;
    private MyTableModel zoomModel = null;
    private MyTableModel detailedModel = null;

    private static JvTable LifeTimeTable = null;
    private JvTable zoomAreaTable = null;

    private JScrollPane tmpScrollPane = new JScrollPane();
    private JScrollPane treeScrollPane = new JScrollPane();
    private JScrollPane tmpScrollPaneZoomArea = new JScrollPane();

    private static ArrayList<Integer> selectedRows = new ArrayList<Integer>();

    protected GlobalDataKeeper globalDataKeeper = null;

    private String[] finalColumns = null;
    private String[][] finalRows = null;

    private String[] finalColumnsZoomArea = null;
    private String[][] finalRowsZoomArea = null;
    private String[] firstLevelUndoColumnsZoomArea = null;
    private String[][] firstLevelUndoRowsZoomArea = null;

    private Integer[] segmentSize = new Integer[4];
    private Integer[] segmentSizeZoomArea = new Integer[4];

    private ArrayList<String> selectedFromTree = new ArrayList<String>();

    private JTree tablesTree = new JTree();
    private JPanel sideMenu = new JPanel();
    private JPanel tablesTreePanel = new JPanel();
    private JPanel descriptionPanel = new JPanel();
    private JLabel treeLabel;
    private JLabel generalTableLabel;
    private JLabel zoomAreaLabel;
    private JLabel descriptionLabel;
    private JTextArea descriptionText;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JButton uniformlyDistributedButton;
    private JButton notUniformlyDistributedButton;
    private JButton showThisToPopup;

    private int[] selectedRowsFromMouse;
    private JvTable tmpLifeTimeTable = null;
    private Integer[] segmentSizeDetailedTable;

    protected int selectedColumn = -1;
    private int selectedColumnZoomArea = -1;

    private int wholeCol = -1;
    private int wholeColZoomArea = -1;

    private int rowHeight = 1;
    private int columnWidth = 1;

    private ArrayList<String> tablesSelected = new ArrayList<String>();

    private boolean showingPld = false;

    private JButton undoButton;
    private JMenu mnProject;
    private JMenuItem mntmInfo;

    protected ProjectConfig projectConfig = ProjectConfig.getInstance();
    protected TableConfig tableConfig = TableConfig.getInstance();
    protected GeneralTableConfig generalTableConfig = GeneralTableConfig.getInstance();
    protected ZoomTableConfig zoomTableConfig = ZoomTableConfig.getInstance();
    protected DetailedTableConfig detailedTableConfig = DetailedTableConfig.getInstance();

    private GeneralTableGraphicComputation generalTableFrameConstruction = GeneralTableGraphicComputation.getInstance();
    public DetailedTableGraphicComputation detailedTableFrameConstruction = DetailedTableGraphicComputation.getInstance();
    private ZoomTableGraphicComputation zoomTableFrameConstruction = ZoomTableGraphicComputation.getInstance();

    protected ProjectInfoListener infoListener = new ProjectInfoListener();
    protected LoadProjectListener loadListener = new LoadProjectListener();
    protected EditProjectListener editListener = new EditProjectListener();
    protected CreateProjectListener createListener = new CreateProjectListener();
    protected ShowPLDListener showPLDListener = new ShowPLDListener();
    protected ShowFullDetailedLifetimeTableListener showfullDetailedTable = new ShowFullDetailedLifetimeTableListener();
    protected ShowPhasesWithClustersPLDListener showPhasesClusters = new ShowPhasesWithClustersPLDListener();
    protected ShowPhasesPLD showPhasesPLDListener = new ShowPhasesPLD();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui frame = new Gui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    // return;
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * Create the frame.
     */
    public Gui() {
        /** DUPLICATED CODE SHOULD EXTRACT METHODS ********/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        createFileJMenu(menuBar);

        tableActionsJMenu(menuBar);

        sideMenu.setBounds(0, 0, 280, 600);
        sideMenu.setBackground(Color.DARK_GRAY);

        GroupLayout gl_sideMenu = new GroupLayout(sideMenu);
        gl_sideMenu.setHorizontalGroup(gl_sideMenu.createParallelGroup(Alignment.LEADING));
        gl_sideMenu.setVerticalGroup(gl_sideMenu.createParallelGroup(Alignment.LEADING));

        sideMenu.setLayout(gl_sideMenu);

        tablesTreePanel.setBounds(10, 400, 260, 180);
        tablesTreePanel.setBackground(Color.LIGHT_GRAY);

        GroupLayout gl_tablesTreePanel = new GroupLayout(tablesTreePanel);
        gl_tablesTreePanel.setHorizontalGroup(gl_tablesTreePanel.createParallelGroup(Alignment.LEADING));
        gl_tablesTreePanel.setVerticalGroup(gl_tablesTreePanel.createParallelGroup(Alignment.LEADING));

        tablesTreePanel.setLayout(gl_tablesTreePanel);

        treeLabel = new JLabel();
        treeLabel.setBounds(10, 370, 260, 40);
        treeLabel.setForeground(Color.WHITE);
        treeLabel.setText("Tree");

        descriptionPanel.setBounds(10, 190, 260, 180);
        descriptionPanel.setBackground(Color.LIGHT_GRAY);

        GroupLayout gl_descriptionPanel = new GroupLayout(descriptionPanel);
        gl_descriptionPanel.setHorizontalGroup(gl_descriptionPanel.createParallelGroup(Alignment.LEADING));
        gl_descriptionPanel.setVerticalGroup(gl_descriptionPanel.createParallelGroup(Alignment.LEADING));

        descriptionPanel.setLayout(gl_descriptionPanel);

        descriptionText = new JTextArea();
        descriptionText.setBounds(5, 5, 250, 170);
        descriptionText.setForeground(Color.BLACK);
        descriptionText.setText("");
        descriptionText.setBackground(Color.LIGHT_GRAY);

        descriptionPanel.add(descriptionText);

        descriptionLabel = new JLabel();
        descriptionLabel.setBounds(10, 160, 260, 40);
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setText("Description");

        sideMenu.add(treeLabel);
        sideMenu.add(tablesTreePanel);

        sideMenu.add(descriptionLabel);
        sideMenu.add(descriptionPanel);

        lifeTimePanel.add(sideMenu);

        JButton buttonHelp = new JButton("Help");
        buttonHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String message = "To open a project, you must select a .txt file that contains the names ONLY of "
                        + "the SQL files of the dataset that you want to use." + "\n"
                        + "The .txt file must have EXACTLY the same name with the folder "
                        + "that contains the DDL Scripts of the dataset." + "\n"
                        + "Both .txt file and dataset folder must be in the same folder.";
                JOptionPane.showMessageDialog(null, message);
            }
        });

        createInfoJMenuItem(menuBar);

        buttonHelp.setBounds(900, 900, 80, 40);
        menuBar.add(buttonHelp);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
                Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1474, Short.MAX_VALUE));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
                Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE));

        tabbedPane.addTab("LifeTime Table", null, lifeTimePanel, null);

        GroupLayout gl_lifeTimePanel = new GroupLayout(lifeTimePanel);
        gl_lifeTimePanel.setHorizontalGroup(
                gl_lifeTimePanel.createParallelGroup(Alignment.LEADING).addGap(0, 1469, Short.MAX_VALUE));
        gl_lifeTimePanel.setVerticalGroup(
                gl_lifeTimePanel.createParallelGroup(Alignment.LEADING).addGap(0, 743, Short.MAX_VALUE));
        lifeTimePanel.setLayout(gl_lifeTimePanel);

        generalTableLabel = new JLabel("Parallel Lives Diagram");
        generalTableLabel.setBounds(300, 0, 150, 30);
        generalTableLabel.setForeground(Color.BLACK);

        zoomAreaLabel = new JLabel();
        zoomAreaLabel.setText("<HTML>Z<br>o<br>o<br>m<br><br>A<br>r<br>e<br>a</HTML>");
        zoomAreaLabel.setBounds(1255, 325, 15, 300);
        zoomAreaLabel.setForeground(Color.BLACK);

        zoomInButton = new JButton("Zoom In");
        zoomInButton.setBounds(1000, 560, 100, 30);

        zoomInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowHeight = rowHeight + 2;
                columnWidth = columnWidth + 1;
                zoomAreaTable.setZoom(rowHeight, columnWidth);

            }
        });

        zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.setBounds(1110, 560, 100, 30);

        zoomOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowHeight = rowHeight - 2;
                columnWidth = columnWidth - 1;
                if (rowHeight < 1) {
                    rowHeight = 1;
                }
                if (columnWidth < 1) {
                    columnWidth = 1;
                }
                zoomAreaTable.setZoom(rowHeight, columnWidth);

            }
        });

        zoomInButton.setVisible(false);
        zoomOutButton.setVisible(false);

        showThisToPopup = new JButton("Enlarge");
        showThisToPopup.setBounds(800, 560, 100, 30);

        showThisToPopup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                EnlargeTable showEnlargmentPopup = new EnlargeTable(finalRowsZoomArea, finalColumnsZoomArea,
                        segmentSizeZoomArea);
                showEnlargmentPopup.setBounds(100, 100, 1300, 700);

                showEnlargmentPopup.setVisible(true);

            }
        });

        showThisToPopup.setVisible(false);

        undoButton = new JButton("Undo");
        undoButton.setBounds(680, 560, 100, 30);

        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (firstLevelUndoColumnsZoomArea != null) {
                    finalColumnsZoomArea = firstLevelUndoColumnsZoomArea;
                    finalRowsZoomArea = firstLevelUndoRowsZoomArea;
                    makeZoomAreaTableForCluster();

                }

            }
        });

        undoButton.setVisible(false);

        uniformlyDistributedButton = new JButton("Same Width");
        uniformlyDistributedButton.setBounds(980, 0, 120, 30);

        uniformlyDistributedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LifeTimeTable.uniformlyDistributed(1);

            }
        });

        uniformlyDistributedButton.setVisible(false);

        notUniformlyDistributedButton = new JButton("Over Time");
        notUniformlyDistributedButton.setBounds(1100, 0, 120, 30);

        notUniformlyDistributedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LifeTimeTable.notUniformlyDistributed(globalDataKeeper);

            }
        });

        notUniformlyDistributedButton.setVisible(false);

        lifeTimePanel.add(zoomInButton);
        lifeTimePanel.add(undoButton);
        lifeTimePanel.add(zoomOutButton);
        lifeTimePanel.add(uniformlyDistributedButton);
        lifeTimePanel.add(notUniformlyDistributedButton);
        lifeTimePanel.add(showThisToPopup);

        lifeTimePanel.add(zoomAreaLabel);

        lifeTimePanel.add(generalTableLabel);

        contentPane.setLayout(gl_contentPane);

        pack();
        setBounds(30, 30, 1300, 700);

    }

    /*button UI stuff no need to change*/
    private void createInfoJMenuItem(JMenuBar menuBar) {
        mnProject = new JMenu("Project");
        menuBar.add(mnProject);

        mntmInfo = new JMenuItem("Info");
        infoListener.listenToGUI(Gui.this);
        mntmInfo.addActionListener(infoListener);
        mnProject.add(mntmInfo);
    }

    private void tableActionsJMenu(JMenuBar menuBar) {
        JMenu mnTable = new JMenu("Table");
        menuBar.add(mnTable);

        JMenuItem mntmShowGeneralLifetimeIDU = new JMenuItem("Show PLD");
        showPLDListener.listenToGUI(Gui.this);
        mntmShowGeneralLifetimeIDU.addActionListener(showPLDListener);
        mnTable.add(mntmShowGeneralLifetimeIDU);

        JMenuItem mntmShowGeneralLifetimePhasesPLD = new JMenuItem("Show Phases PLD");
        showPhasesPLDListener.listenToGui(Gui.this);
        mntmShowGeneralLifetimePhasesPLD.addActionListener(showPhasesPLDListener);
        mnTable.add(mntmShowGeneralLifetimePhasesPLD);

        JMenuItem mntmShowGeneralLifetimePhasesWithClustersPLD = new JMenuItem("Show Phases With Clusters PLD");

        showPhasesClusters.listenToGui(Gui.this);
        mntmShowGeneralLifetimePhasesWithClustersPLD.addActionListener(showPhasesClusters);
        mnTable.add(mntmShowGeneralLifetimePhasesWithClustersPLD);

        JMenuItem mntmShowLifetimeTable = new JMenuItem("Show Full Detailed LifeTime Table");
        showfullDetailedTable.listenToGui(Gui.this);
        mntmShowLifetimeTable.addActionListener(showfullDetailedTable);
        mnTable.add(mntmShowLifetimeTable);
    }

    private void createFileJMenu(JMenuBar menuBar) {
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmCreateProject = new JMenuItem("Create Project");
        createListener.listenToGui(Gui.this);
        mntmCreateProject.addActionListener(createListener);
        mnFile.add(mntmCreateProject);

        JMenuItem mntmLoadProject = new JMenuItem("Load Project");
        loadListener.listenToGui(Gui.this);
        mntmLoadProject.addActionListener(loadListener);
        mnFile.add(mntmLoadProject);

        JMenuItem mntmEditProject = new JMenuItem("Edit Project");
        editListener.listenToGui(Gui.this);
        mntmEditProject.addActionListener(editListener);
        mnFile.add(mntmEditProject);
    }
    /************************/

    public void paintGeneralTableIDU(MyTableModel myTable) {
        final IDUTableRenderer renderer = new IDUTableRenderer(Gui.this, finalRowsZoomArea, globalDataKeeper,
                segmentSize);

        final JvTable generalTable = generalTableFrameConstruction.makeGeneralGraphicTable(myTable, rowHeight, columnWidth,
                wholeCol);

        generalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        generalTable.setDefaultRenderer(Object.class, renderer);

        generalTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);

                String tmpValue = finalRowsZoomArea[row][column];
                String columnName = table.getColumnName(column);
                Color fr = new Color(0, 0, 0);

                c.setForeground(fr);
                setOpaque(true);

                if (column == wholeColZoomArea && wholeColZoomArea != 0) {

                    // Refactored: EXTRACT METHOD and MOVE METHOD
                    String description = globalDataKeeper
                            .constructDescriptionZoomAreaColumn(table.getColumnName(column));

                    descriptionText.setText(description);

                    Color cl = new Color(255, 69, 0, 100);

                    c.setBackground(cl);
                    return c;
                }

                else if (selectedColumnZoomArea == 0) {

                    if (isSelected) {
                        Color cl = new Color(255, 69, 0, 100);
                        c.setBackground(cl);

                        // Refactored: EXTRACT METHOD and MOVE METHOD
                        String description = globalDataKeeper
                                .constructDescriptionZoomAreaRow(finalRowsZoomArea[row][0]);

                        descriptionText.setText(description);

                        return c;

                    }
                } else {

                    if (selectedFromTree.contains(finalRowsZoomArea[row][0])) {

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);

                        return c;
                    }

                    if (isSelected && hasFocus) {

                        String description = "";
                        if (!table.getColumnName(column).contains("Table name")) {

                            // Refactored: EXTRACT METHOD and MOVE METHOD
                            description = globalDataKeeper.constructDescriptionZoomAreaCell(table.getColumnName(column),
                                    finalRowsZoomArea[row][0]);
                            descriptionText.setText(description);
                        }
                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);

                        return c;
                    }

                }

                try {
                    int numericValue = Integer.parseInt(tmpValue);
                    Color insersionColor = null;
                    setToolTipText(Integer.toString(numericValue));

                    if (numericValue == 0) {
                        insersionColor = new Color(154, 205, 50, 200);
                    } else if (numericValue > 0 && numericValue <= segmentSizeZoomArea[3]) {
                        insersionColor = new Color(176, 226, 255);
                    } else if (numericValue > segmentSizeZoomArea[3] && numericValue <= 2 * segmentSizeZoomArea[3]) {
                        insersionColor = new Color(92, 172, 238);
                    } else if (numericValue > 2 * segmentSizeZoomArea[3]
                            && numericValue <= 3 * segmentSizeZoomArea[3]) {

                        insersionColor = new Color(28, 134, 238);
                    } else {
                        insersionColor = new Color(16, 78, 139);
                    }
                    c.setBackground(insersionColor);

                    return c;
                } catch (Exception e) {

                    if (tmpValue.equals("")) {
                        c.setBackground(Color.GRAY);
                        return c;
                    } else {
                        if (columnName.contains("v")) {
                            c.setBackground(Color.lightGray);
                            setToolTipText(columnName);
                        } else {
                            Color tableNameColor = new Color(205, 175, 149);
                            c.setBackground(tableNameColor);
                        }
                        return c;
                    }

                }
            }

        });

        generalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    selectedRowsFromMouse = target.getSelectedRows();
                    selectedColumnZoomArea = target.getSelectedColumn();
                    renderer.setSelCol(selectedColumnZoomArea);
                    target.getSelectedColumns();

                    zoomAreaTable.repaint();
                }

            }
        });

        generalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    JTable target1 = (JTable) e.getSource();
                    target1.getSelectedColumns();
                    selectedRowsFromMouse = target1.getSelectedRows();
                    System.out.println(target1.getSelectedColumns().length);
                    System.out.println(target1.getSelectedRow());
                    for (int rowsSelected = 0; rowsSelected < selectedRowsFromMouse.length; rowsSelected++) {
                        System.out.println(generalTable.getValueAt(selectedRowsFromMouse[rowsSelected], 0));
                    }
                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Clear Selection");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selectedFromTree = new ArrayList<String>();
                            zoomAreaTable.repaint();
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(generalTable, e.getX(), e.getY());

                }

            }
        });

        /** changed general table to zoomtablearea **/
        generalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wholeColZoomArea = generalTable.columnAtPoint(e.getPoint());
                renderer.setWholeCol(generalTable.columnAtPoint(e.getPoint()));
                String name = generalTable.getColumnName(wholeColZoomArea);
                System.out.println("Column index selected rrreee" + wholeColZoomArea + " " + name);
                generalTable.repaint();
            }
        });

        generalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Clear Column Selection");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            wholeColZoomArea = -1;
                            renderer.setWholeCol(wholeColZoomArea);

                            generalTable.repaint();
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(generalTable, e.getX(), e.getY());

                }

            }

        });

        zoomAreaTable = generalTable;
        tmpScrollPaneZoomArea.setViewportView(zoomAreaTable);
        tmpScrollPaneZoomArea.setAlignmentX(0);
        tmpScrollPaneZoomArea.setAlignmentY(0);
        tmpScrollPaneZoomArea.setBounds(300, 300, 950, 250);
        tmpScrollPaneZoomArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tmpScrollPaneZoomArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        lifeTimePanel.setCursor(getCursor());
        lifeTimePanel.add(tmpScrollPaneZoomArea);
    }
   
    public void defineButtonsVisibillity(boolean isVisible) {
        showingPld = isVisible;
        zoomInButton.setVisible(isVisible);
        zoomOutButton.setVisible(isVisible);
        showThisToPopup.setVisible(isVisible);
    }

    public void makeGeneralTablePhases() {
        selectedRows = new ArrayList<Integer>();
        //generalModel = tableConfig.createGeneralTableModel(finalRows, finalColumns);
        generalModel = generalTableConfig.createTableModel(finalRows,finalColumns);

        final JvTable generalTable = new JvTable(generalModel);

        generalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        generalTable.setShowGrid(false);
        generalTable.setIntercellSpacing(new Dimension(0, 0));

        for (int i = 0; i < generalTable.getColumnCount(); i++) {
            if (i == 0) {
                generalTable.getColumnModel().getColumn(0).setPreferredWidth(86);

            } else {

                generalTable.getColumnModel().getColumn(i).setPreferredWidth(1);

            }
        }

        generalTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);

                String tmpValue = finalRows[row][column];
                String columnName = table.getColumnName(column);
                Color fr = new Color(0, 0, 0);
                c.setForeground(fr);

                if (column == wholeCol && wholeCol != 0) {

                    // Refactored: EXTRACT METHOD AND MOVE METHOD
                    String description = globalDataKeeper.constructDescriptionPLDColumns(table.getColumnName(column),
                            column);

                    descriptionText.setText(description);

                    Color cl = new Color(255, 69, 0, 100);

                    c.setBackground(cl);
                    return c;
                } else if (selectedColumn == 0) {
                    if (isSelected) {
                        String description;
                        if (finalRows[row][0].contains("Cluster")) {
                            // Refactored: EXTRACT METHOD AND MOVE METHOD
                            description = globalDataKeeper.constructDescriptionPLDCluster(row, finalRows[row][0]);

                        } else {
                            // Refactored: EXTRACT METHOD AND MOVE METHOD
                            description = globalDataKeeper.constructDescriptionPLDPhases(finalRows[row][0]);
                        }

                        descriptionText.setText(description);
                        Color cl = new Color(255, 69, 0, 100);
                        c.setBackground(cl);
                        return c;
                    }
                } else {

                    if (selectedFromTree.contains(finalRows[row][0])) {

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);

                        return c;
                    }

                    if (isSelected && hasFocus) {

                        String description = "";
                        if (!table.getColumnName(column).contains("Table name")) {

                            if (finalRows[row][0].contains("Cluster")) {

                                // Refactored: EXTRACT METHOD AND MOVE METHOD
                                description = globalDataKeeper.constructDescriptionPLDCell(table.getColumnName(column),
                                        row, column, tmpValue, finalRows[row][0]);

                            } else {
                                // Refactored: EXTRACT METHOD AND MOVE METHOD
                                description = globalDataKeeper.constructDescriptionPLDPhasesCell(
                                        table.getColumnName(column), column, tmpValue);
                            }

                            descriptionText.setText(description);

                        }

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);
                        return c;
                    }

                }

                try {
                    int numericValue = Integer.parseInt(tmpValue);
                    Color insersionColor = null;
                    setToolTipText(Integer.toString(numericValue));

                    if (numericValue == 0) {
                        insersionColor = new Color(154, 205, 50, 200);
                    } else if (numericValue > 0 && numericValue <= segmentSize[3]) {

                        insersionColor = new Color(176, 226, 255);
                    } else if (numericValue > segmentSize[3] && numericValue <= 2 * segmentSize[3]) {
                        insersionColor = new Color(92, 172, 238);
                    } else if (numericValue > 2 * segmentSize[3] && numericValue <= 3 * segmentSize[3]) {

                        insersionColor = new Color(28, 134, 238);
                    } else {
                        insersionColor = new Color(16, 78, 139);
                    }
                    c.setBackground(insersionColor);

                    return c;
                } catch (Exception e) {

                    if (tmpValue.equals("")) {
                        c.setBackground(Color.gray);
                        return c;
                    } else {
                        if (columnName.contains("v")) {
                            c.setBackground(Color.lightGray);
                            setToolTipText(columnName);
                        } else {
                            Color tableNameColor = new Color(205, 175, 149);
                            c.setBackground(tableNameColor);
                        }
                        return c;
                    }

                }
            }
        });

        generalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    selectedRowsFromMouse = target.getSelectedRows();
                    selectedColumn = target.getSelectedColumn();
                    LifeTimeTable.repaint();
                }

            }
        });

        generalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("Right Click");

                    JTable target1 = (JTable) e.getSource();
                    selectedColumn = target1.getSelectedColumn();
                    selectedRowsFromMouse = new int[target1.getSelectedRows().length];
                    selectedRowsFromMouse = target1.getSelectedRows();

                    final String sSelectedRow = (String) generalTable.getValueAt(target1.getSelectedRow(), 0);
                    tablesSelected = new ArrayList<String>();

                    for (int rowsSelected = 0; rowsSelected < selectedRowsFromMouse.length; rowsSelected++) {
                        tablesSelected.add((String) generalTable.getValueAt(selectedRowsFromMouse[rowsSelected], 0));
                    }

                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Show Details for the selection");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent le) {
                            if (sSelectedRow.contains("Cluster ")) {
                                showClusterSelectionToZoomArea(selectedColumn, sSelectedRow);

                            } else {
                                showSelectionToZoomArea(selectedColumn);
                            }
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    JMenuItem clearSelectionItem = new JMenuItem("Clear Selection");
                    clearSelectionItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent le) {

                            selectedFromTree = new ArrayList<String>();
                            LifeTimeTable.repaint();
                        }
                    });
                    popupMenu.add(clearSelectionItem);
                    popupMenu.show(generalTable, e.getX(), e.getY());

                }

            }
        });

        generalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wholeCol = generalTable.columnAtPoint(e.getPoint());
                String name = generalTable.getColumnName(wholeCol);
                System.out.println("Column index selected " + wholeCol + " " + name);
                generalTable.repaint();
                if (showingPld) {
                    zoomModel = zoomTableConfig.createTableModel(finalRowsZoomArea, finalColumnsZoomArea);
//                    generalTable = generalTableConstruction.makeGeneralTableIDU(zoomModel, rowHeight, columnWidth, wholeCol);

                    // generalTable =
                    // tableConfig.makeGeneralTableIDU(tableConfig.createZoomTableModel(finalRowsZoomArea,
                    // finalColumnsZoomArea, rowHeight, columnWidth),rowHeight,rowHeight,wholeCol);
                    paintGeneralTableIDU(zoomModel);
                    // makeGeneralTableIDU();
                    rowHeight = generalTableFrameConstruction.getRowHeight();
                    columnWidth = generalTableFrameConstruction.getColumnWidth();
                }
            }
        });

        generalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem clearColumnSelectionItem = new JMenuItem("Clear Column Selection");
                    clearColumnSelectionItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            wholeCol = -1;
                            generalTable.repaint();
                            if (showingPld) {
                                zoomModel = zoomTableConfig.createTableModel(finalRowsZoomArea,
                                        finalColumnsZoomArea);
                                // generalTable =
                                // tableConfig.makeGeneralTableIDU(tableConfig.createZoomTableModel(finalRowsZoomArea,
                                // finalColumnsZoomArea, rowHeight, columnWidth),
                                // rowHeight,columnWidth,wholeCol);

                                paintGeneralTableIDU(zoomModel);
                                // makeGeneralTableIDU();

                                rowHeight = generalTableFrameConstruction.getRowHeight();
                                columnWidth = generalTableFrameConstruction.getColumnWidth();
                            }
                        }
                    });
                    popupMenu.add(clearColumnSelectionItem);
                    JMenuItem showDetailsItem = new JMenuItem("Show Details for this Phase");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String sSelectedRow = finalRows[0][0];
                            System.out.println("?" + sSelectedRow);
                            tablesSelected = new ArrayList<String>();
                            for (int i = 0; i < finalRows.length; i++)
                                tablesSelected.add((String) generalTable.getValueAt(i, 0));

                            if (!sSelectedRow.contains("Cluster ")) {

                                showSelectionToZoomArea(wholeCol);
                            } else {
                                showClusterSelectionToZoomArea(wholeCol, "");
                            }

                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(generalTable, e.getX(), e.getY());

                }

            }

        });

        LifeTimeTable = generalTable;

        tmpScrollPane.setViewportView(LifeTimeTable);
        tmpScrollPane.setAlignmentX(0);
        tmpScrollPane.setAlignmentY(0);
        tmpScrollPane.setBounds(300, 30, 950, 265);
        tmpScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tmpScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        lifeTimePanel.setCursor(getCursor());
        lifeTimePanel.add(tmpScrollPane);

    }

    private void showSelectionToZoomArea(int selectedColumn) {

        TableConstructionZoomArea table = new TableConstructionZoomArea(globalDataKeeper, tablesSelected,
                selectedColumn);
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        segmentSizeZoomArea = table.getSegmentSize();

        System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
        System.out.println("C: " + columns.length + " R: " + rows.length);

        finalColumnsZoomArea = columns;
        finalRowsZoomArea = rows;
        tabbedPane.setSelectedIndex(0);
        makeZoomAreaTable();

    }
    
    private void showClusterSelectionToZoomArea(int selectedColumn, String selectedCluster) {
        ArrayList<String> tablesOfCluster = new ArrayList<String>();
        for (int i = 0; i < tablesSelected.size(); i++) {
            String[] selectedClusterSplit = tablesSelected.get(i).split(" ");
            int cluster = Integer.parseInt(selectedClusterSplit[1]);
            ArrayList<String> namesOfTables = globalDataKeeper.getClusterCollectors().get(0).getClusters().get(cluster)
                    .getNamesOfTables();
            for (int j = 0; j < namesOfTables.size(); j++) {
                tablesOfCluster.add(namesOfTables.get(j));
            }
            System.out.println(tablesSelected.get(i));
        }

        PldConstruction table;
        if (selectedColumn == 0) {
            table = new TableConstructionClusterTablesPhasesZoomA(globalDataKeeper, tablesOfCluster);
        } else {
            table = new TableConstructionZoomArea(globalDataKeeper, tablesOfCluster, selectedColumn);
        }
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        segmentSizeZoomArea = table.getSegmentSize();
        System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
        System.out.println("C: " + columns.length + " R: " + rows.length);

        finalColumnsZoomArea = columns;
        finalRowsZoomArea = rows;
        tabbedPane.setSelectedIndex(0);
        makeZoomAreaTableForCluster();

    }

    private void makeZoomAreaTable() {
        showingPld = false;
        int numberOfColumns = finalRowsZoomArea[0].length;
        int numberOfRows = finalRowsZoomArea.length;

        final String[][] rowsZoom = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {

            rowsZoom[i][0] = finalRowsZoomArea[i][0];

        }

        zoomModel = new MyTableModel(finalColumnsZoomArea, rowsZoom);

        final JvTable zoomTable = zoomTableFrameConstruction.makeZoomGraphicTable(zoomModel,20,20);//new JvTable(zoomModel);


        /*
        zoomTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        zoomTable.setShowGrid(false);
        zoomTable.setIntercellSpacing(new Dimension(0, 0));

        for (int i = 0; i < zoomTable.getColumnCount(); i++) {
            if (i == 0) {
                zoomTable.getColumnModel().getColumn(0).setPreferredWidth(150);

            } else {

                zoomTable.getColumnModel().getColumn(i).setPreferredWidth(20);
                zoomTable.getColumnModel().getColumn(i).setMaxWidth(20);
                zoomTable.getColumnModel().getColumn(i).setMinWidth(20);
            }
        }
        */
        zoomTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);

                String tmpValue = finalRowsZoomArea[row][column];
                String columnName = table.getColumnName(column);
                Color fr = new Color(0, 0, 0);
                c.setForeground(fr);

                if (column == wholeColZoomArea) {
                    // Refactored: EXTRACT METHOD AND MOVE METHOD
                    String description = globalDataKeeper.constructDescriptionZoomAreaPhasesColumn(rowsZoom,
                            table.getColumnName(column), column);

                    descriptionText.setText(description);
                    Color cl = new Color(255, 69, 0, 100);
                    c.setBackground(cl);

                    return c;
                } else if (selectedColumnZoomArea == 0) {
                    if (isSelected) {
                        // Refactored: EXTRACT METHOD AND MOVE METHOD
                        String description = globalDataKeeper.constructDescriptionZoomAreaPhasesRow(
                                table.getColumnName(column), table.getColumnName(1),
                                table.getColumnName(table.getColumnCount() - 1));
                        descriptionText.setText(description);

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);
                        return c;
                    }
                } else {
                    if (isSelected && hasFocus) {

                        String description = "";
                        if (!table.getColumnName(column).contains("Table name")) {
                            // Refactored: EXTRACT METHOD AND MOVE METHOD
                            description = globalDataKeeper
                                    .constructDescriptionZoomAreaPhasesRowCell(table.getColumnName(column));
                            descriptionText.setText(description);
                        }

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);
                        return c;
                    }

                }

                try {
                    int numericValue = Integer.parseInt(tmpValue);
                    Color insersionColor = null;
                    setToolTipText(Integer.toString(numericValue));

                    if (numericValue == 0) {
                        insersionColor = new Color(0, 100, 0);
                    } else if (numericValue > 0 && numericValue <= segmentSizeZoomArea[3]) {

                        insersionColor = new Color(176, 226, 255);
                    } else if (numericValue > segmentSizeZoomArea[3] && numericValue <= 2 * segmentSizeZoomArea[3]) {
                        insersionColor = new Color(92, 172, 238);
                    } else if (numericValue > 2 * segmentSizeZoomArea[3]
                            && numericValue <= 3 * segmentSizeZoomArea[3]) {

                        insersionColor = new Color(28, 134, 238);
                    } else {
                        insersionColor = new Color(16, 78, 139);
                    }
                    c.setBackground(insersionColor);

                    return c;
                } catch (Exception e) {

                    if (tmpValue.equals("")) {
                        c.setBackground(Color.DARK_GRAY);
                        return c;
                    } else {
                        if (columnName.contains("v")) {
                            c.setBackground(Color.lightGray);
                            setToolTipText(columnName);
                        } else {
                            Color tableNameColor = new Color(205, 175, 149);
                            c.setBackground(tableNameColor);
                        }
                        return c;
                    }

                }
            }
        });

        zoomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    selectedRowsFromMouse = target.getSelectedRows();
                    selectedColumnZoomArea = target.getSelectedColumn();
                    zoomAreaTable.repaint();
                }

            }
        });

        zoomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    JTable target1 = (JTable) e.getSource();
                    selectedColumnZoomArea = target1.getSelectedColumn();
                    selectedRowsFromMouse = target1.getSelectedRows();
                    System.out.println(target1.getSelectedColumn());
                    System.out.println(target1.getSelectedRow());
                    final ArrayList<String> tablesSelected = new ArrayList<String>();
                    for (int rowsSelected = 0; rowsSelected < selectedRowsFromMouse.length; rowsSelected++) {
                        tablesSelected.add((String) zoomTable.getValueAt(selectedRowsFromMouse[rowsSelected], 0));
                        System.out.println(tablesSelected.get(rowsSelected));
                    }

                }

            }
        });

        // listener
        zoomTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wholeColZoomArea = zoomTable.columnAtPoint(e.getPoint());
                String name = zoomTable.getColumnName(wholeColZoomArea);
                System.out.println("Column index selected " + wholeCol + " " + name);
                zoomTable.repaint();
            }
        });

        zoomTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Clear Column Selection");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            wholeColZoomArea = -1;
                            zoomTable.repaint();
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(zoomTable, e.getX(), e.getY());

                }

            }

        });

        zoomAreaTable = zoomTable;

        tmpScrollPaneZoomArea.setViewportView(zoomAreaTable);
        tmpScrollPaneZoomArea.setAlignmentX(0);
        tmpScrollPaneZoomArea.setAlignmentY(0);
        tmpScrollPaneZoomArea.setBounds(300, 300, 950, 250);
        tmpScrollPaneZoomArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tmpScrollPaneZoomArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        lifeTimePanel.setCursor(getCursor());
        lifeTimePanel.add(tmpScrollPaneZoomArea);
    }

    private void makeZoomAreaTableForCluster() {
        showingPld = false;
        undoButton.setVisible(true);

        int numberOfColumns = finalRowsZoomArea[0].length;
        int numberOfRows = finalRowsZoomArea.length;

        final String[][] rowsZoom = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {

            rowsZoom[i][0] = finalRowsZoomArea[i][0];
        }

        zoomModel = new MyTableModel(finalColumnsZoomArea, rowsZoom);

        final JvTable zoomTable = zoomTableFrameConstruction.makeZoomGraphicTable(zoomModel,70,10);//new JvTable(zoomModel);

        /*
        zoomTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        zoomTable.setShowGrid(false);
        zoomTable.setIntercellSpacing(new Dimension(0, 0));

        for (int i = 0; i < zoomTable.getColumnCount(); i++) {
            if (i == 0) {
                zoomTable.getColumnModel().getColumn(0).setPreferredWidth(150);

            } else {

                zoomTable.getColumnModel().getColumn(i).setPreferredWidth(10);
                zoomTable.getColumnModel().getColumn(i).setMaxWidth(10);
                zoomTable.getColumnModel().getColumn(i).setMinWidth(70);
            }
        }
        */
        zoomTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);

                String tmpValue = finalRowsZoomArea[row][column];
                String columnName = table.getColumnName(column);
                Color fr = new Color(0, 0, 0);
                c.setForeground(fr);

                if (column == wholeColZoomArea && wholeColZoomArea != 0) {

                    // Refactored: EXTRACT METHOD AND MOVE METHOD
                    String description = globalDataKeeper
                            .constructDescriptionZoomAreaClusterColumn(table.getColumnName(column), column);
                    descriptionText.setText(description);

                    Color cl = new Color(255, 69, 0, 100);

                    c.setBackground(cl);
                    return c;
                } else if (selectedColumnZoomArea == 0) {
                    if (isSelected) {

                        // Refactored: EXTRACT METHOD AND MOVE METHOD
                        finalRowsZoomArea = zoomTableConfig.getFinalRowsZoomArea();
                        String description = globalDataKeeper
                                .constructDescriptionZoomAreaClusterRow(finalRowsZoomArea[row][0]);
                        descriptionText.setText(description);

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);
                        return c;
                    }
                } else {

                    if (isSelected && hasFocus) {

                        String description = "";
                        if (!table.getColumnName(column).contains("Table name")) {

                            // Refactored: EXTRACT METHOD AND MOVE METHOD
                            finalRowsZoomArea = zoomTableConfig.getFinalRowsZoomArea();
                            description = globalDataKeeper.constructDescriptionZoomAreaClusterCell(
                                    table.getColumnName(column), tmpValue, finalRowsZoomArea[row][0]);
                            descriptionText.setText(description);

                        }

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);
                        return c;
                    }

                }

                try {
                    int numericValue = Integer.parseInt(tmpValue);
                    Color insersionColor = null;
                    setToolTipText(Integer.toString(numericValue));

                    if (numericValue == 0) {
                        insersionColor = new Color(0, 100, 0);
                    } else if (numericValue > 0 && numericValue <= segmentSizeZoomArea[3]) {

                        insersionColor = new Color(176, 226, 255);
                    } else if (numericValue > segmentSizeZoomArea[3] && numericValue <= 2 * segmentSizeZoomArea[3]) {
                        insersionColor = new Color(92, 172, 238);
                    } else if (numericValue > 2 * segmentSizeZoomArea[3]
                            && numericValue <= 3 * segmentSizeZoomArea[3]) {

                        insersionColor = new Color(28, 134, 238);
                    } else {
                        insersionColor = new Color(16, 78, 139);
                    }
                    c.setBackground(insersionColor);

                    return c;
                } catch (Exception e) {

                    if (tmpValue.equals("")) {
                        c.setBackground(Color.DARK_GRAY);
                        return c;
                    } else {
                        if (columnName.contains("v")) {
                            c.setBackground(Color.lightGray);
                            setToolTipText(columnName);
                        } else {
                            Color tableNameColor = new Color(205, 175, 149);
                            c.setBackground(tableNameColor);
                        }
                        return c;
                    }

                }
            }

        });

        zoomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    selectedRowsFromMouse = target.getSelectedRows();
                    selectedColumnZoomArea = target.getSelectedColumn();
                    zoomAreaTable.repaint();
                }

            }
        });

        zoomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    JTable target1 = (JTable) e.getSource();
                    selectedColumnZoomArea = target1.getSelectedColumn();
                    selectedRowsFromMouse = target1.getSelectedRows();
                    System.out.println(target1.getSelectedColumn());
                    System.out.println(target1.getSelectedRow());

                    tablesSelected = new ArrayList<String>();

                    for (int rowsSelected = 0; rowsSelected < selectedRowsFromMouse.length; rowsSelected++) {
                        tablesSelected.add((String) zoomTable.getValueAt(selectedRowsFromMouse[rowsSelected], 0));
                        System.out.println(tablesSelected.get(rowsSelected));
                    }
                    if (zoomTable.getColumnName(selectedColumnZoomArea).contains("Phase")) {

                        final JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem showDetailsItem = new JMenuItem("Show Details");
                        showDetailsItem.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                firstLevelUndoColumnsZoomArea = finalColumnsZoomArea;
                                firstLevelUndoRowsZoomArea = finalRowsZoomArea;
                                showSelectionToZoomArea(selectedColumnZoomArea);

                            }
                        });
                        popupMenu.add(showDetailsItem);
                        popupMenu.show(zoomTable, e.getX(), e.getY());
                    }

                }

            }
        });

        // listener
        zoomTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wholeColZoomArea = zoomTable.columnAtPoint(e.getPoint());
                String name = zoomTable.getColumnName(wholeColZoomArea);
                System.out.println("Column index selected " + wholeCol + " " + name);
                zoomTable.repaint();
            }
        });

        zoomTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Clear Column Selection");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            wholeColZoomArea = -1;
                            zoomTable.repaint();
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(zoomTable, e.getX(), e.getY());

                }

            }

        });

        zoomAreaTable = zoomTable;

        tmpScrollPaneZoomArea.setViewportView(zoomAreaTable);
        tmpScrollPaneZoomArea.setAlignmentX(0);
        tmpScrollPaneZoomArea.setAlignmentY(0);
        tmpScrollPaneZoomArea.setBounds(300, 300, 950, 250);
        tmpScrollPaneZoomArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tmpScrollPaneZoomArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        lifeTimePanel.setCursor(getCursor());
        lifeTimePanel.add(tmpScrollPaneZoomArea);
    }

    /**nothing to do here*/
    public static class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }

            int selectedRow = LifeTimeTable.getSelectedRow();

            selectedRows.add(selectedRow);
        }
    }

    public static class ColumnListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
        }
    }
    /*********/

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    /* no use for this one*/
    public void optimize() throws IOException {

        String tableInfo = "Birth Weight:" + "\tDeath Weight:" + "\tChange Weight:" + "\tTotal Cohesion:"
                + "\tTotal Separation:" + "\n";
        int counter = 0;
        for (double wb = 0.0; wb <= 1.0; wb = wb + 0.01) {

            for (double wd = (1.0 - wb); wd >= 0.0; wd = wd - 0.01) {

                double wc = 1.0 - (wb + wd);
                TableClusteringMainEngine tableClusteringEngine = new TableClusteringMainEngine(globalDataKeeper, wb, wd, wc);
                Integer numberOfClusters=14;
                tableClusteringEngine.extractClusters(numberOfClusters);
                globalDataKeeper.setClusterCollectors(tableClusteringEngine.getClusterCollectors());

                ClusterValidatorMainEngine lala = new ClusterValidatorMainEngine(globalDataKeeper);
                lala.run();

                tableInfo = tableInfo + wb + "\t" + wd + "\t" + wc + "\t" + lala.getTotalCohesion() + "\t"
                        + lala.getTotalSeparation() + "\t" + (wb + wd + wc) + "\n";

                counter++;
                System.err.println(counter);

            }

        }

        FileWriter fw;
        try {
            fw = new FileWriter("lala.csv");

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(tableInfo);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(tableInfo);

    }

    /* no use for this one*/
    public void getExternalValidityReport() throws IOException {

        String validityInfo = "Birth Weight:" + "\tDeath Weight:" + "\tChange Weight:" + "\n";
        int counter = 0;

        TableClusteringMainEngine mainEngine2 = new TableClusteringMainEngine(globalDataKeeper, 0.333, 0.333, 0.333);
        mainEngine2.extractClusters(4);
        globalDataKeeper.setClusterCollectors(mainEngine2.getClusterCollectors());

        ClusterValidatorMainEngine clusterValidatorMainEngine = new ClusterValidatorMainEngine(globalDataKeeper);
        clusterValidatorMainEngine.run();

        validityInfo = validityInfo + "\n" + "0.333" + "\t" + "0.333" + "\t" + "0.333" + "\n"
                + clusterValidatorMainEngine.getExternalEvaluationReport();

        for (double wb = 0.0; wb <= 1.0; wb = wb + 0.5) {

            for (double wd = (1.0 - wb); wd >= 0.0; wd = wd - 0.5) {

                double wc = 1.0 - (wb + wd);
                mainEngine2 = new TableClusteringMainEngine(globalDataKeeper, wb, wd, wc);
                mainEngine2.extractClusters(4);
                globalDataKeeper.setClusterCollectors(mainEngine2.getClusterCollectors());

                clusterValidatorMainEngine = new ClusterValidatorMainEngine(globalDataKeeper);
                clusterValidatorMainEngine.run();

                validityInfo = validityInfo + "\n" + wb + "\t" + wd + "\t" + wc + "\n" + clusterValidatorMainEngine.getExternalEvaluationReport();

                counter++;
                System.err.println(counter);

            }

        }

        FileWriter fw;
        try {
            fw = new FileWriter("lala.csv");

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(validityInfo);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(validityInfo);

    }

    public void fillTable() {
        TableConstructionIDU table = new TableConstructionIDU(globalDataKeeper);
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        segmentSizeZoomArea = table.getSegmentSize();

        finalColumnsZoomArea = columns;
        finalRowsZoomArea = rows;
        tabbedPane.setSelectedIndex(0);
        zoomModel = zoomTableConfig.createTableModel(finalRowsZoomArea, finalColumnsZoomArea);

        paintGeneralTableIDU(zoomModel);

        finalRowsZoomArea = zoomTableConfig.getFinalRowsZoomArea();
        finalColumnsZoomArea = zoomTableConfig.getFinalColumnsZoomArea();

        Float timeWeight = (float) 0.5;
        Float changeWeight = (float) 0.5;
        Integer numberOfPhases;
        boolean preProcessingTime = false;
        boolean preProcessingChange = false;
        if (globalDataKeeper.getAllPPLTransitions().size() < 56) {
            numberOfPhases = 40;
        } else {
            numberOfPhases = 56;
        }
        Integer numberOfClusters = 14;

        System.out.println(timeWeight + " " + changeWeight);

        PhaseAnalyzerMainEngine mainEngine = new PhaseAnalyzerMainEngine(projectConfig, timeWeight, changeWeight,
                preProcessingTime, preProcessingChange);

        Double b = new Double(0.3);
        Double d = new Double(0.3);
        Double c = new Double(0.3);

        mainEngine.parseInput();
        System.out.println("\n\n\n");
        mainEngine.extractPhases(numberOfPhases);

        mainEngine.connectTransitionsWithPhases(globalDataKeeper);
        globalDataKeeper.setPhaseCollectors(mainEngine.getPhaseCollectors());
        TableClusteringMainEngine mainEngine2 = new TableClusteringMainEngine(globalDataKeeper, b, d, c);
        mainEngine2.extractClusters(numberOfClusters);
        globalDataKeeper.setClusterCollectors(mainEngine2.getClusterCollectors());
        mainEngine2.print();

        if (globalDataKeeper.getPhaseCollectors().size() != 0) {
            TableConstructionWithClusters tableP = new TableConstructionWithClusters(globalDataKeeper);
            final String[] columnsP = tableP.constructColumns();
            final String[][] rowsP = tableP.constructRows();
            segmentSize = tableP.getSegmentSize();
            finalColumns = columnsP;
            finalRows = rowsP;
            tabbedPane.setSelectedIndex(0);
            makeGeneralTablePhases();
            fillClustersTree();
        }
        System.out.println("Schemas:" + globalDataKeeper.getAllPPLSchemas().size());
        System.out.println("Transitions:" + globalDataKeeper.getAllPPLTransitions().size());
        System.out.println("Tables:" + globalDataKeeper.getAllPPLTables().size());
    }

    public void fillTree() {

        TreeConstructionGeneral tc = new TreeConstructionGeneral(globalDataKeeper);
        tablesTree = new JTree();
        tablesTree = tc.constructTree();
        tablesTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent ae) {
                TreePath selection = ae.getPath();
                selectedFromTree.add(selection.getLastPathComponent().toString());
                System.out.println(selection.getLastPathComponent().toString() + " is selected");

            }
        });

        PPLSchema temp = ((PPLSchema) globalDataKeeper.getAllPPLSchemas().values().toArray()[0]);
        PPLSchema temp2 = ((PPLSchema) globalDataKeeper.getAllPPLSchemas().values().toArray()[1]);

        tablesTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click Tree");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Show This into the Table");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            LifeTimeTable.repaint();

                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(tablesTree, e.getX(), e.getY());
                }

            }
        });

        treeScrollPane.setViewportView(tablesTree);

        treeScrollPane.setBounds(5, 5, 250, 170);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablesTreePanel.add(treeScrollPane);

        treeLabel.setText("General Tree");

        sideMenu.revalidate();
        sideMenu.repaint();

    }

    public void fillPhasesTree() {

        TreeConstructionPhases tc = new TreeConstructionPhases(globalDataKeeper);
        tablesTree = tc.constructTree();

        tablesTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent ae) {
                TreePath selection = ae.getPath();
                selectedFromTree.add(selection.getLastPathComponent().toString());
                System.out.println(selection.getLastPathComponent().toString() + " is selected");

            }
        });

        tablesTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click Tree");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Show This into the Table");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            LifeTimeTable.repaint();

                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(tablesTree, e.getX(), e.getY());

                }

            }
        });

        treeScrollPane.setViewportView(tablesTree);
        treeScrollPane.setBounds(5, 5, 250, 170);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablesTreePanel.add(treeScrollPane);

        treeLabel.setText("Phases Tree");

        sideMenu.revalidate();
        sideMenu.repaint();

    }

    public void fillClustersTree() {

        TreeConstructionPhasesWithClusters tc = new TreeConstructionPhasesWithClusters(globalDataKeeper);
        tablesTree = tc.constructTree();

        tablesTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent ae) {
                TreePath selection = ae.getPath();
                selectedFromTree.add(selection.getLastPathComponent().toString());
                System.out.println(selection.getLastPathComponent().toString() + " is selected");

            }
        });

        tablesTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click Tree");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Show This into the Table");
                    showDetailsItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LifeTimeTable.repaint();
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(tablesTree, e.getX(), e.getY());

                }

            }
        });

        treeScrollPane.setViewportView(tablesTree);

        treeScrollPane.setBounds(5, 5, 250, 170);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablesTreePanel.add(treeScrollPane);

        treeLabel.setText("Clusters Tree");

        sideMenu.revalidate();
        sideMenu.repaint();

    }


    /*getters/setters*/
    public void getDataKeeper() {
        globalDataKeeper = projectConfig.getGlobalDataKeeper();
    }

    public void setDescription(String descr) {
        descriptionText.setText(descr);
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    /*public String getCurrentProject() {
        return currentProject;
    }*/

    public DetailedTableGraphicComputation getDetailedTableFrameConstruction() {
        return detailedTableFrameConstruction;
    }

    public int getWholeCol() {
        return wholeCol;
    }

    public void setWholeCol(int wholeCol) {
        this.wholeCol = wholeCol;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setFinalColumns(String[] finalColumns) {
        this.finalColumns = finalColumns;
    }

    public void setFinalRows(String[][] finalRows) {
        this.finalRows = finalRows;
    }

    public void setSegmentSize(Integer[] segmentSize) {
        this.segmentSize = segmentSize;
    }

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public MyTableModel getGeneralModel() {
        return generalModel;
    }

    public MyTableModel getZoomModel() {
        zoomModel = zoomTableConfig.getZoomModel();
        return zoomModel;
    }

    public DetailedTableConfig getDetailedTableConfig() {
        return detailedTableConfig;
    }

    public MyTableModel getDetailedModel() {
        return detailedModel;
    }

    public void setZoomModel(MyTableModel zoomModel) {
        this.zoomModel = zoomModel;
    }

    public void setFinalColumnsZoomArea(String[] finalColumnsZoomArea) {
        this.finalColumnsZoomArea = finalColumnsZoomArea;
    }

    public void setFinalRowsZoomArea(String[][] finalRowsZoomArea) {
        this.finalRowsZoomArea = finalRowsZoomArea;
    }

    public void setSegmentSizeZoomArea(Integer[] segmentSizeZoomArea) {
        this.segmentSizeZoomArea = segmentSizeZoomArea;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public Integer[] getSegmentSizeDetailedTable() {
        return segmentSizeDetailedTable;
    }

    public void setTmpLifeTimeTable(JvTable tmpLifeTimeTable) {
        this.tmpLifeTimeTable = tmpLifeTimeTable;
    }

    public void setSegmentSizeDetailedTable(Integer[] segmentSizeDetailedTable) {
        this.segmentSizeDetailedTable = segmentSizeDetailedTable;
    }

}

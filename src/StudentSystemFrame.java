import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @Author Yifan Wu
 * Date on 2021/4/21  14:57
 */
public class StudentSystemFrame extends JFrame {
    private static List<Student> students;
    private static TextArea textArea = new TextArea();
    private static JPanel buttons = new JPanel();
    private static JPanel listButtons = new JPanel();
    private static JPanel subjectButtons = new JPanel();
    private static JPanel sortChoiceButtons = new JPanel();
    private static JPanel ascChoiceButtons = new JPanel();
    private static JPanel confirmPanel = new JPanel();
    private static int asc ;
    private static int sortChoice ;
    private static int subject ;
    private static Box stuTextBox = Box.createVerticalBox();
    private static JTextField stuIdTextField=new JTextField(20);
    private static JTextField stuNameTextField=new JTextField(20);
    private static JTextField stuAgeTextField=new JTextField(20);
    private static JTextField mathScoreTextField=new JTextField(20);
    private static JTextField englishScoreTextField=new JTextField(20);
    private static JTextField computerScoreTextField=new JTextField(20);
    StudentSystemFrame(){
        super("学生信息系统");
        Container content = getContentPane();
        content.setLayout(new BorderLayout());

        //文字区域
        JTextField textField = new JTextField();
        Font courierNew = new Font("Courier New", Font.BOLD, 20);
        textField.setFont(courierNew);
        textArea.setFont(courierNew);
        stuIdTextField.setFont(courierNew);
        stuNameTextField.setFont(courierNew);
        stuAgeTextField.setFont(courierNew);
        mathScoreTextField.setFont(courierNew);
        englishScoreTextField.setFont(courierNew);
        computerScoreTextField.setFont(courierNew);


        //按钮区域

        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        listButtons.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        subjectButtons.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        sortChoiceButtons.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        ascChoiceButtons.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        confirmPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式

        //North区域
        Box topBox = Box.createVerticalBox();
        topBox.add(textField);
        topBox.add(listButtons);
        topBox.add(buttons);
        topBox.add(sortChoiceButtons);
        sortChoiceButtons.setVisible(false);
        topBox.add(subjectButtons);
        subjectButtons.setVisible(false);
        topBox.add(ascChoiceButtons);
        ascChoiceButtons.setVisible(false);
        topBox.add(confirmPanel);
        confirmPanel.setVisible(false);
        content.add(topBox,BorderLayout.NORTH);
        //文字输入区域

        addPanel();
        topBox.add(stuTextBox);


//        topBox.add(stuNameTextField);
//        topBox.add(stuAgeTextField);
//        topBox.add(mathScoreTextField);
//        topBox.add(englishScoreTextField);
//        topBox.add(computerScoreTextField);



        //中心区域

        content.add(textArea,BorderLayout.CENTER);

        /**
         * 查询功能
         */
        //初始化学生列表
        readFromText();

        /**
         * 查询
         */
        JButton btn1 = new JButton("查询所有");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(""); //清空
                for(Student student:students){
                    textArea.append(student.disp()+"\n");
                }
            }
        });
        listButtons.add(btn1);

        JButton btn2 = new JButton("按学号查询");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText();
                textArea.setText("");
                selectById(Integer.parseInt(id));

            }
        });
        buttons.add(btn2);

        JButton btn3 = new JButton("按姓名查询");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                textArea.setText("");
                selectByName(name);
            }
        });
        buttons.add(btn3);

        JButton btn4 = new JButton("按年龄查询");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String age = textField.getText();
                textArea.setText("");
                selectByAge(Integer.parseInt(age));
            }
        });
        buttons.add(btn4);



        /**
         * 查询所有学号
         */
        JButton idButton = new JButton("学号列表");
        idButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<Integer> idSet = new TreeSet<>();
                for(Student student:students){
                    idSet.add(student.id);
                }

                textArea.setText(""); //清空
                textArea.append("学号"+"\n");
                for(int id:idSet){
                    textArea.append(id+"\n");
                }
            }
        });
        listButtons.add(idButton);

        /**
         * 查询所有姓名
         */
        JButton nameButton = new JButton("姓名列表");
        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<String> nameSet = new TreeSet<>();
                for(Student student:students){
                    nameSet.add(student.name);
                }

                textArea.setText(""); //清空
                textArea.append("姓名"+"\n");
                for(String name:nameSet){
                    textArea.append(name+"\n");
                }
            }
        });
        listButtons.add(nameButton);

        JButton ageButton = new JButton("年龄列表");
        ageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<Integer> ageSet = new TreeSet<>();
                for(Student student:students){
                    ageSet.add(student.age);
                }

                textArea.setText(""); //清空
                textArea.append("年龄"+"\n");
                for(int age:ageSet){
                    textArea.append(age+"\n");
                }
            }
        });
        listButtons.add(ageButton);

        /**
         * 排序功能
         */
        JButton btn5 = new JButton("排序");
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortChoiceButtons.setVisible(true);
            }
        });
        buttons.add(btn5);
        addSortChoiceButtons();
        addSubjectButtons();
        addAscChoiceButton();
        JButton confirmButton = new JButton("确认");
        confirmPanel.add(confirmButton);
        confirmPanel.setVisible(false);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subjectButtons.setVisible(false);
                sortChoiceButtons.setVisible(false);
                ascChoiceButtons.setVisible(false);
                confirmPanel.setVisible(false);
                sortByChoice(sortChoice,asc,subject);
                showInfo();
            }
        });

        /**
         * 增加信息功能
         */
        JButton addButton = new JButton("添加学生信息");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuTextBox.setVisible(true);
            }
        });
        buttons.add(addButton);

        /**
         * 返回按钮
         */
        JButton returnButton = new JButton("返回");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textArea.setText("");
                subjectButtons.setVisible(false);
                sortChoiceButtons.setVisible(false);
                ascChoiceButtons.setVisible(false);
                confirmPanel.setVisible(false);
                stuTextBox.setVisible(false);
            }
        });
        buttons.add(returnButton);


        JButton addConfirmButton = new JButton("确认添加");
        addConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(stuIdTextField.getText());
                String name = stuNameTextField.getText();
                int age = Integer.parseInt(stuAgeTextField.getText());
                int mathScore = Integer.parseInt(mathScoreTextField.getText());
                int englishScore = Integer.parseInt(englishScoreTextField.getText());
                int computerScore = Integer.parseInt(computerScoreTextField.getText());
                addStudentInfo(id,name,age,mathScore,englishScore,computerScore);
                stuTextBox.setVisible(false);
            }
        });
        JPanel addConfirmPanel = new JPanel();
        addConfirmPanel.add(addConfirmButton);
        addConfirmPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
        stuTextBox.add(addConfirmPanel);

    }

    private void addPanel() {
        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("学号:");
        idPanel.add(idLabel);
        idPanel.add(stuIdTextField);
        idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        stuTextBox.add(idPanel);

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("姓名:");
        namePanel.add(nameLabel);
        namePanel.add(stuNameTextField);
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        stuTextBox.add(namePanel);

        JPanel agePanel = new JPanel();
        JLabel ageLabel = new JLabel("年龄:");
        agePanel.add(ageLabel);
        agePanel.add(stuAgeTextField);
        agePanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        stuTextBox.add(agePanel);

        JPanel mathPanel = new JPanel();
        JLabel mathLabel = new JLabel("数学成绩:");
        mathPanel.add(mathLabel);
        mathPanel.add(mathScoreTextField);
        mathPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        stuTextBox.add(mathPanel);

        JPanel englishPanel = new JPanel();
        JLabel englishLabel = new JLabel("英语成绩:");
        englishPanel.add(englishLabel);
        englishPanel.add(englishScoreTextField);
        englishPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        stuTextBox.add(englishPanel);

        JPanel computerPanel = new JPanel();
        JLabel computerLabel = new JLabel("计算机成绩:");
        computerPanel.add(computerLabel);
        computerPanel.add(computerScoreTextField);
        computerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); //设置样式
        stuTextBox.add(computerPanel);
        stuTextBox.setVisible(false);
    }

    private void addStudentInfo(int stuId,String stuName,int stuAge,int mathScore,int englishScore,int computerScore){
        //将输入的学生信息封装为学生对象
        Student newStudent = new Student(stuId,stuName,stuAge,mathScore,englishScore,computerScore);
        //将该对象转化为String写入到txt文件中
        TextWriter textWriter = new TextWriter("students.txt");
        textWriter.write(newStudent.toText());
        readFromText();
    }

    private void showInfo(){
        textArea.setText("");
        for(Student student:students){
            textArea.append(student.disp()+"\n");
        }
    }

    private void addSortChoiceButtons() {
        JButton idButton = new JButton("学号");
        idButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortChoice=1;
                ascChoiceButtons.setVisible(true);
            }
        });
        JButton nameButton = new JButton("姓名");
        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortChoice=2;
                ascChoiceButtons.setVisible(true);
            }
        });
        JButton ageButton = new JButton("年龄");
        ageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortChoice=3;
                ascChoiceButtons.setVisible(true);
            }
        });
        JButton sumScoreButton = new JButton("总分");
        sumScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortChoice=4;
                ascChoiceButtons.setVisible(true);
            }
        });
        JButton singleScoreButton = new JButton("单科");
        singleScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortChoice=5;
                subjectButtons.setVisible(true);
            }
        });
        sortChoiceButtons.add(idButton);
        sortChoiceButtons.add(nameButton);
        sortChoiceButtons.add(ageButton);
        sortChoiceButtons.add(sumScoreButton);
        sortChoiceButtons.add(singleScoreButton);
        sortChoiceButtons.setVisible(false);
    }
    private void addSubjectButtons() {
        JButton mathButton = new JButton("数学");
        mathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject=1;
                ascChoiceButtons.setVisible(true);
            }
        });
        JButton englishButton = new JButton("英语");
        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject=2;
                ascChoiceButtons.setVisible(true);
            }
        });
        JButton computerButton = new JButton("计算机");
        computerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject=3;
                ascChoiceButtons.setVisible(true);
            }
        });
        subjectButtons.add(mathButton);
        subjectButtons.add(englishButton);
        subjectButtons.add(computerButton);
        subjectButtons.setVisible(false);
    }

    public static StudentSystemFrame studentSystemFrame = new StudentSystemFrame();

    private void addAscChoiceButton(){
        JButton reverse = new JButton("降序");
        reverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asc = -1;
                confirmPanel.setVisible(true);
            }
        });
        ascChoiceButtons.add(reverse);

        JButton ascBtn = new JButton("升序");
        ascBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asc = 1;
                confirmPanel.setVisible(true);
            }
        });
        ascChoiceButtons.add(ascBtn);
    }

    private static void readFromText() {
        TextReader textReader = new TextReader("students.txt");
        String text = textReader.getText();
        String[] totalInfo = text.split("\n");
//        System.out.println(Arrays.asList(info));
        students = new ArrayList<>();
        for(String studentInfo:totalInfo){
            String[] info = studentInfo.split(",");
            students.add(new Student(Integer.parseInt(info[0]),info[1],Integer.parseInt(info[2]),
                    Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5])));
        }
//        System.out.println(students);
    }

    private static void selectByAge(int age) {
        for(Student student:students){
            if(student.age==age){
                textArea.append(student.disp()+"\n");
            }
        }
    }

    private static void selectByName(String name) {
        for(Student student:students){
            if(student.name.equals(name)){
                textArea.append(student.disp()+"\n");
            }
        }
    }

    private static void selectById(Integer id) {
        for(Student student:students){
            if(student.id.equals(id)){
                textArea.append(student.disp()+"\n");
            }
        }
    }

    private static void sortByChoice(int sortChoice,int asc,int subject) {
        switch (sortChoice){
            case 1:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return asc*(o1.id-o2.id);
                    }
                });
                break;
            case 2:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return asc*o1.name.compareTo(o2.name);
                    }
                });
                break;
            case 3:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return asc*(o1.age-o2.age);
                    }
                });
                break;
            case 4:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return asc*(o1.totalScore-o2.totalScore);
                    }
                });
                break;
            case 5:
                switch (subject){
                    case 1:
                        Collections.sort(students, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return asc*(o1.mathScore-o2.mathScore);
                            }
                        });
                        break;
                    case 2:
                        Collections.sort(students, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return asc*(o1.englishScore-o2.englishScore);
                            }
                        });
                        break;
                    case 3:
                        Collections.sort(students, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return asc*(o1.computerScore-o2.computerScore);
                            }
                        });
                        break;

                }

        }
    }

    public static void main(String[] args) {
        Toolkit toolkit = studentSystemFrame.getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        studentSystemFrame.setBounds(screenSize.width/4,screenSize.height/4,2*screenSize.width/3,2*screenSize.height/3);
        studentSystemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentSystemFrame.setVisible(true);
    }
}

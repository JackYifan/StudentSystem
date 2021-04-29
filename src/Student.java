/**
 * @Author Yifan Wu
 * Date on 2021/4/14  19:17
 */
public class Student{
    Integer id ;
    String name ;
    Integer age ;
    Integer mathScore;
    Integer englishScore;
    Integer computerScore;
    Integer totalScore;
    public Student(){}

    public Student(Integer id, String name, Integer age, Integer mathScore, Integer englishScore, Integer computerScore) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        this.computerScore = computerScore;
        totalScore = mathScore+englishScore+computerScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMathScore() {
        return mathScore;
    }

    public void setMathScore(Integer mathScore) {
        this.mathScore = mathScore;
    }

    public Integer getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(Integer englishScore) {
        this.englishScore = englishScore;
    }

    public Integer getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(Integer computerScore) {
        this.computerScore = computerScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }


    public String disp() {
        return "学号: "+id+"\t"+" 姓名: "+name+"\t"+" 年龄: "+age+"\t"+" 数学得分: "+mathScore+"\t"+" 英语得分: "
                +englishScore+"\t"+" 计算机得分: "+computerScore+"\t"+" 总分: "+totalScore;
    }

    public String toText(){
        return id+","+name+","+age+","+mathScore+","+englishScore+","+computerScore+","+totalScore;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mathScore=" + mathScore +
                ", englishScore=" + englishScore +
                ", computerScore=" + computerScore +
                ", totalScore=" + totalScore +
                '}';
    }
}

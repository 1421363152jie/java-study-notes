package wenjiang;

public class Student implements Comparable<Student>{
      private String stunu;//ѧ��
      private String name;//����
      private String gender;//�Ա�
      private String age;//����
      private String height;//���
      private String weight;//����
      private String phone;//�绰
	public String getStunu() {
		return stunu;
	}
	public void setStunu(String stunu) {
		this.stunu = stunu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Student [stunu=" + stunu + ", name=" + name + ", gender="
				+ gender + ", age=" + age + ", height=" + height + ", weight="
				+ weight + ", phone=" + phone + "\n]";
	}
	@Override
	public int compareTo(Student o) {
	    
		return this.stunu.compareTo(o.stunu);
	}
      
	  

}

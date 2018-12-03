package wenjiang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fileut {
	
	public static void main(String[] args) {
		parseFile();
		writeFile();
	}
	
	//定义存储学生对象的集合
	static List<Student> slit=new ArrayList<Student>();
	//解析文件parseFile()
     public static void parseFile() {
    	 //定义字符缓冲区
    	BufferedReader br=null;
    	try {
			br=new BufferedReader(new FileReader("src/TCPwenjiang/Student.txt"));
			//定义是否是第一行的变量
			boolean isFirst =true;
			//定义存储行的变量
			String line=null;
			while((line=br.readLine())!=null){
				//根据每一个行通过空格切分
				String[] arr=line.split(" ");
				//如果是第一行
				if(isFirst){
					//遍历第一行的学号,使用普通for
					for(int i=1;i<arr.length;i++){
						//创建学生对象
					Student s=new Student();
					//把学生加入集合
					slit.add(s);
					}
					isFirst=false;
				}
				//给学生赋值属性
				setAtr(arr);
			}
		
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
			if(br!=null){
				
					br.close();
			}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			
			}
		}
     }
     public static void setAtr(String arr[]){
    	//获得每一行的头
			String head=arr[0];
			//循环行的每一个拆分项，从第二项开始
			for(int i=1;i<arr.length;i++){
				//在集合中根据索引获得学生对象
				Student s=slit.get(i-1);
				switch (head) {
				case "学号":
					s.setStunu(arr[i]);
					break;
				case "姓名":
					s.setName(arr[i]);
					break;	
				case "性别":
					s.setGender(arr[i]);
					break;
				case "年龄":
					s.setAge(arr[i]);
					break;
				case "身高":
					s.setHeight(arr[i]);
					break;
				case "体重":
					s.setWeight(arr[i]);
					break;
				case "电话":
					s.setPhone(arr[i]);
					break;
				default:
					break;
				}
			}
			
		}
     //把集合写入文件
	public static void writeFile(){
		//排序
		Collections.sort(slit);
		PrintWriter pw=null;
		try {
			 pw=new PrintWriter("Students.txt");
			pw.println("学号\t姓名\t性别\t年龄\t身高\t体重\t电话");
			for(Student s: slit){
				pw.println(s.getStunu()+"\t"+s.getName()+"\t"+s.getGender()+"\t"+
			s.getAge()+"\t"+s.getHeight()+"\t"+s.getWeight()+"\t"+s.getPhone()+"\t");
				pw.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
	}
}
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
	
	//����洢ѧ������ļ���
	static List<Student> slit=new ArrayList<Student>();
	//�����ļ�parseFile()
     public static void parseFile() {
    	 //�����ַ�������
    	BufferedReader br=null;
    	try {
			br=new BufferedReader(new FileReader("src/TCPwenjiang/Student.txt"));
			//�����Ƿ��ǵ�һ�еı���
			boolean isFirst =true;
			//����洢�еı���
			String line=null;
			while((line=br.readLine())!=null){
				//����ÿһ����ͨ���ո��з�
				String[] arr=line.split(" ");
				//����ǵ�һ��
				if(isFirst){
					//������һ�е�ѧ��,ʹ����ͨfor
					for(int i=1;i<arr.length;i++){
						//����ѧ������
					Student s=new Student();
					//��ѧ�����뼯��
					slit.add(s);
					}
					isFirst=false;
				}
				//��ѧ����ֵ����
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
    	//���ÿһ�е�ͷ
			String head=arr[0];
			//ѭ���е�ÿһ�������ӵڶ��ʼ
			for(int i=1;i<arr.length;i++){
				//�ڼ����и����������ѧ������
				Student s=slit.get(i-1);
				switch (head) {
				case "ѧ��":
					s.setStunu(arr[i]);
					break;
				case "����":
					s.setName(arr[i]);
					break;	
				case "�Ա�":
					s.setGender(arr[i]);
					break;
				case "����":
					s.setAge(arr[i]);
					break;
				case "���":
					s.setHeight(arr[i]);
					break;
				case "����":
					s.setWeight(arr[i]);
					break;
				case "�绰":
					s.setPhone(arr[i]);
					break;
				default:
					break;
				}
			}
			
		}
     //�Ѽ���д���ļ�
	public static void writeFile(){
		//����
		Collections.sort(slit);
		PrintWriter pw=null;
		try {
			 pw=new PrintWriter("Students.txt");
			pw.println("ѧ��\t����\t�Ա�\t����\t���\t����\t�绰");
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
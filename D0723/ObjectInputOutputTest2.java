package D0723;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;


class  ObjectData implements Serializable{
	String name;
	int age;
	float height;
	public ObjectData() {
		this("Noname", 0, 0.0f);
	}
	
	public ObjectData(String name, int age, float height) {
		this.name = name;
		this.age = age;
		this.height = height;
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException{
		stream.defaultWriteObject();
		System.out.print("writeObject: ");
	}
	
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
		stream.defaultReadObject();
		System.out.println("readObject: ");
	}
	
	public String toString() {
		return("name: " + name + ", age: " + age + ", height: " + height);
	} 
}

public class ObjectInputOutputTest2 {

	public static void main(String[] args) {
		ObjectData[] data = new ObjectData[3];
		data[0] = new ObjectData("Yongwoo", 26, 168f);
		data[1] = new ObjectData("Kildong", 19, 174.8f);
		data[2] = new ObjectData("Dongsoo", 30, 169.4f);
		try {
			FileOutputStream outf = new FileOutputStream("AaTxtTest.txt");
			ObjectOutput out = new ObjectOutputStream(outf);
			out.writeInt(data.length);
			System.out.println("Number: " + data.length);
			//Object 개수만큼 Object를 출력한다.
			for(int i=0;i<data.length;i++) {
				out.writeObject(data[i]);
				System.out.println(data[i]);
			}
			out.close();
			FileInputStream inf = new FileInputStream("AaTxtTest.txt");
			ObjectInput in = new ObjectInputStream(inf);
			int numberOfData;
			ObjectData objData;
			numberOfData = in.readInt();
			System.out.println("Number: " + numberOfData);
			//Object개수만큼 읽어 들인다.
			for(int i=0;i<numberOfData;i++) {
				objData = (ObjectData)in.readObject();
				System.out.println(objData);
			}
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}

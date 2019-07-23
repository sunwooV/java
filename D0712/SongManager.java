package D0712;

public class SongManager {

	public static void main(String[] args) {
		Song s = new Song("Dansing Queen", "ABBA", "Arrival",
				new String[] {"Benny Andersson", "Bjorn Ulvaeus"},1977,2);
		s.show();

	}

}

class Song{
	private String title;
	private String artist;
	private String album;
	private String[] composer;
	private int year;
	private int track;
	Song(){
		title = null;
		artist = null;
		album = null;
		composer = null;
		year = 0;
		track = 0;
	}
	Song(String title, String artist, String album, String[] composer, int year, int track){
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.composer = composer;
		this.year = year;
		this.track = track;
	}
	public void show() {
		System.out.println("�뷡 ����:" + title);
		System.out.println("����: " + artist);
		System.out.println("�ٹ�: " + album);
		  System.out.print("�۰:");
		  for (int i=0; i<composer.length; i++) {
		    System.out.print(composer[i]);
		    if (i+1 == composer.length)
		     System.out.println();
		    else
		     System.out.print(",");
		  }
		  System.out.println("�⵵:" + year);
		  System.out.println("Ʈ�� ��ȣ:" + track);
	}
}

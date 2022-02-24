import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.Random;

public class main {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	Vector<Data> dt = new Vector<>();
	Vector<String> bonus = new Vector<>();
	String kode, nama, jenisKelamin, jabatan, newKode;
	int Choose, gaji, manager = 0, supervisor = 0, admin = 0;

	public main() {
		// TODO Auto-generated constructor stub
		do {
			System.out.println("Menu:");
			System.out.println("1. Insert Data");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data");
			System.out.println("5. Exit");
			System.out.print("Choose Menu:");
			Choose = sc.nextInt();
			sc.nextLine();

			switch (Choose) {
			case 1:
				insert();
				break;
			case 2:
				view();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			}
		} while (Choose != 5);

	}

	public void insert() {

		do {
			kode = "\0";
			kode += (char) (rand.nextInt(26) + 'A');
			kode += (char) (rand.nextInt(26) + 'A');
			kode += "-";
			kode += (rand.nextInt(10));
			kode += (rand.nextInt(10));
			kode += (rand.nextInt(10));
			kode += (rand.nextInt(10));
		} while (dt.indexOf(kode) == 0);

		do {
			System.out.print("Input nama karyawan [>=3]: ");
			nama = sc.nextLine();
		} while (nama.length() < 3);

		do {
			System.out.print("Input jenis kelamin [Laki-Laki | Perempuan]: ");
			jenisKelamin = sc.nextLine();
		} while (!(jenisKelamin.equals("Laki-Laki") || jenisKelamin.equals("Perempuan")));

		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin]: ");
			jabatan = sc.nextLine();
		} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));

		jobCount();

		Data datalist = new Data(kode, nama, jenisKelamin, jabatan, gaji);
		dt.add(datalist);

		System.out.println("Press enter to continue...");
		sc.nextLine();

	}

	public void jobCount() {
		if (jabatan.equals("Manager")) {
			gaji = 8000000;
			manager++;
			if ((manager - 1) != 0 && (manager - 1) % 3 == 0) {
				man();
			}
		} else if (jabatan.equals("Supervisor")) {
			gaji = 6000000;
			supervisor++;
			if ((supervisor - 1) != 0 && (supervisor - 1) % 3 == 0) {
				visor();
			}
		} else {
			gaji = 4000000;
			admin++;
			if ((admin - 1) != 0 && (admin - 1) % 3 == 0) {
				adm();
			}
		}
		bonus.clear();
	}

	public void man() {
		for (int i = 0; i < dt.size(); i++) {
			if (dt.get(i).job.equals("Manager")) {
				dt.get(i).payment += dt.get(i).payment * 10 / 100;
				newKode = dt.get(i).code;
				bonus.add(newKode);
			}
		}
		System.out.print("Bonus sebesar 10% telah diberikan ke karyawan dengan id ");
		for (int i = 0; i < bonus.size(); i++) {
			if (i == bonus.size() - 1) {
				System.out.printf("%s.\n", bonus.get(i));
			} else {
				System.out.printf("%s, ", bonus.get(i));
			}
		}
	}

	public void visor() {
		for (int i = 0; i < dt.size(); i++) {
			if (dt.get(i).job.equals("Supervisor")) {
				dt.get(i).payment += dt.get(i).payment * 75 / 1000;
				newKode = dt.get(i).code;
				bonus.add(newKode);
			}
		}
		System.out.print("Bonus sebesar 7.5% telah diberikan ke karyawan dengan id ");
		for (int i = 0; i < bonus.size(); i++) {
			if (i == bonus.size() - 1) {
				System.out.printf("%s.\n", bonus.get(i));
			} else {
				System.out.printf("%s, ", bonus.get(i));
			}
		}
	}

	public void adm() {
		for (int i = 0; i < dt.size(); i++) {
			if (dt.get(i).job.equals("Admin")) {
				dt.get(i).payment += dt.get(i).payment * 5 / 100;
				newKode = dt.get(i).code;
				bonus.add(newKode);
			}
		}
		System.out.print("Bonus sebesar 5% telah diberikan ke karyawan dengan id ");
		for (int i = 0; i < bonus.size(); i++) {
			if (i == bonus.size() - 1) {
				System.out.printf("%s.\n", bonus.get(i));
			} else {
				System.out.printf("%s, ", bonus.get(i));
			}
		}
	}

	public void view() {
		if (dt.isEmpty()) {
			System.out.println("Data Not Found");
			System.out.println("Press enter to continue...");
			sc.nextLine();
		} else {
			ascending();
			System.out.println(
					"|----|-----------------|------------------------------|---------------|--------------|-------------|");
			System.out.printf("|%-4s|%-17s|%-30s|%-15s|%-14s|%-13s|\n", "No", "Kode Karyawan", "Nama Karyawan",
					"Jenis Kelamin", "Jabatan", "Gaji Karyawan");
			System.out.println(
					"|----|-----------------|------------------------------|---------------|--------------|-------------|");
			for (int i = 0; i < dt.size(); i++) {
				System.out.printf("|%4s|%17s|%30s|%15s|%14s|%13d|\n", (i + 1), dt.get(i).code, dt.get(i).name,
						dt.get(i).gender, dt.get(i).job, dt.get(i).payment);
			}
			System.out.println(
					"|----|-----------------|------------------------------|---------------|--------------|-------------|");
		}
	}

	public void ascending() {
		for (int i = 0; i < dt.size() - 1; i++) {
			for (int j = 0; j < dt.size() - i - 1; j++) {
				if (dt.get(j).name.compareTo(dt.get(j + 1).name) > 0) {
					Data temp = dt.get(j);
					dt.set(j, dt.get(j + 1));
					dt.set(j + 1, temp);
				}
			}
		}
	}

	public void update() {
		if (dt.isEmpty()) {
			System.out.println("Data Not Found");
			System.out.println("Press enter to continue...");
		} else {
			view();
			int idx;
			do {
				System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
				idx = sc.nextInt();
				sc.nextLine();
			} while (idx < 1 || idx > dt.size());

			do {
				System.out.print("Input nama karyawan [>=3]: ");
				nama = sc.nextLine();
			} while (!(nama.length() >= 3 || nama.equals("0")));
			if (!nama.equals("0")) {
				dt.get(idx - 1).name = nama;
			}

			do {
				System.out.print("Input jenis kelamin [Laki-Laki | Perempuan]: ");
				jenisKelamin = sc.nextLine();
			} while (!(jenisKelamin.equals("Laki-Laki") || jenisKelamin.equals("Perempuan")
					|| jenisKelamin.equals("0")));
			if (!jenisKelamin.equals("0")) {
				dt.get(idx - 1).gender = jenisKelamin;
			}

			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin]: ");
				jabatan = sc.nextLine();
			} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")
					|| jabatan.equals("0")));
			if (!jabatan.equals("0")) {
				if (dt.get(idx - 1).job.equals("Manager")) {
					manager--;
				} else if (dt.get(idx - 1).job.equals("Supervisor")) {
					supervisor--;
				} else {
					admin--;
				}
				jobCount();

				dt.get(idx - 1).payment = gaji;
				dt.get(idx - 1).job = jabatan;
			}
			System.out.printf("Berhasil mengupdate karyawan dengan id %s\n", dt.get(idx - 1).code);
			System.out.println("Press enter to continue...");
		}
		sc.nextLine();

	}

	public void delete() {
		if (dt.isEmpty()) {
			System.out.println("Data Not Found");
			System.out.println("Press enter to continue...");
		} else {
			view();
			int idx;
			do {
				System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
				idx=sc.nextInt();sc.nextLine();
			}while(idx<1||idx>dt.size());
			
			System.out.printf("Karyawan dengan kode %s berhasil dihapus\n", dt.get(idx-1).code);
			dt.remove(idx-1);
			System.out.println("Press enter to continue...");
		}
		sc.nextLine();
	}

	public static void main(String[] args) {
		new main();
	}

}

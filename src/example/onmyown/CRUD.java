package example.onmyown;
import java.util.Scanner;
public class CRUD {
    static String menus[] = {
        "입력",
        "조회",
        "수정",
        "삭제",
        "모두조회",
        "종료"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DAO dao = new DAO();

        do {
            int menu = menuselect(sc);
            switch (menu) {
                case 1:
                    insert(sc, dao);
                    break;
                case 2:
                    select(sc, dao);
                    break;
                case 3:
                    update(sc, dao);
                    break;
                case 4:
                    delete(sc, dao);
                    break;
                case 5:
                    selectAll(dao);
                    break;
                case 6:
                    sc.close();
                    return;
            }
        } while (true);
    }

    private static int menuselect(Scanner sc) {
        int choice;
        do {
            displayMenu();
            choice = inputNumber(sc, 1, menus.length);
        } while (choice == -1); // Continue until valid choice is made
        return choice;
    }

    private static void displayMenu() {
        System.out.println("================================================");
        for (int i = 0; i < menus.length; i++) {
            System.out.println((i + 1) + "." + menus[i]);
        }
        System.out.println("================================================");
        System.out.print("메뉴를 선택하세요> ");
    }

    private static int inputNumber(Scanner sc, int start, int end) {
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine());
                if (input <= end && input >= start) {
                    break;
                } else {
                    System.out.println(start + "~" + end + "사이의 숫자를 입력하세요>");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력하세요>");
            }
        }
        return input;
    }

    private static void insert(Scanner sc, DAO dao) {
        System.out.print("이름을 입력하세요>");
        String name = sc.nextLine();
        int kor = inputNumber(sc,0,100);
        int math = inputNumber(sc,0,100);
        int eng = inputNumber(sc,0,100);
        
        Student3 student = new Student3(name,kor,math,eng);
        
        int result = dao.insert(student);
        if (result ==1) {
        	System.out.println("삽입 되었습니다.");
        } else {
        	System.out.println("삽입 실패하였습니다.");
        }
    }

    private static void select(Scanner sc, DAO dao) {
        // Implement your select logic here
        System.out.println("조회 메소드 완료");
    }

    private static void update(Scanner sc, DAO dao) {
        // Implement your update logic here
        System.out.println("수정 메소드 완료");
    }

    private static void delete(Scanner sc, DAO dao) {
        // Implement your delete logic here
        System.out.println("삭제 메소드 완료");
    }

    private static void selectAll(DAO dao) {
        // Implement your selectAll logic here
        System.out.println("모두조회 메소드 완료");
    }
}

	/*package example;

import java.util.Scanner;

public class CRUD {
	static String menus[] = { "입력", "조회", "수정", "삭제", "모두조회", "종료" };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		DAO dao = new DAO();	
	}
	
	do {
		int menu = menuselect(sc);
		switch (menu) {
		case 1:
			insert(sc, dao);
			break;
		case 2:
			select(sc, dao);
			break;
		case 3:
			update(sc, dao);
			break;
		case 4:
			delete(sc, dao);
			break;
		case 5:
			selectAll(dao);
			break;
		case 6:
			sc.close();
			return;
		}
	} while (true);
}
}
		
	private static int menuselect(Scanner sc) {
		int i = 0;
		System.out.println("================================================");
		for (String m : menus) {
			System.out.println(++i + "." + m);
		}
		System.out.println("================================================");
		System.out.print("메뉴를 선택하세요>");
		return inputNumber(sc,1,menus.length);

	}
	
	

	private static int inputNumber(Scanner sc, int start, int end) {
		int input = 0;
		while (true)
			try {
				input = Integer.parseInt(sc.nextLine());
				if(input <= end && input >= start) {
					break;
				} else {
					System.out.println(start + "~" + end + "사이의 숫자를 입력하세요>");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자로 입력하세요>");
			}
		return input;
		
		
		
		String search_word = "";
			if (menu != 6)
				search_word = searchData(sc, 1,menus.length);
			return search_word;
		}

	private static String searchData(Scanner sc, int menu) {
		System.out.print("조회할 " + menus[menu - 1] + "를(을) 입력하세요>");
		return sc.next();
	}
		
	 */		
	
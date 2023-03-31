import java.util.*;

// в задаче четко сказано что прямоугольник. ни линия
// и не точка!
public class PlayGround {
    private ArrayList<ArrayList<Character>> cityMap = new ArrayList<>();
    private int length;
    private int width;
    private ArrayList<Integer> emptyAreas = new ArrayList<>();

    // Очертание карты создается рандомно. Появление *
    // внутри с вероятностью 1 к  18
    public void giveMeData() {
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split("\\s");
        
        int n = Integer.parseInt(strArr[0]);
        int m = Integer.parseInt(strArr[1]);
        in.close();
        for (int i = 0; i < n; i++) {
            ArrayList<Character> cityMapRow = new ArrayList<>();
            if (i == 0 || i == (n-1)) {
                for (int j = 0; j < m; j++) {
                    cityMapRow.add('*');
                }
                
            }
            else {

                for (int j = 0; j < m; j++) {
                    if (j == 0 || j == (m-1)) {
                        cityMapRow.add('*');
                    }
                    else {
                        Random random = new Random();
                        // здесь задается вероятность 1 к 18
                        int x = random.nextInt(18);  
                        if (x == 0) {
                            cityMapRow.add('*');
                        }
                        else {
                            cityMapRow.add('.');
                        }
                    }
                }
            }

            this.cityMap.add(cityMapRow);
        }
    }

    public void findStart() {
        for (int i = 1; i < this.cityMap.size()-1; i++) {
            for (int j = 1; j < this.cityMap.get(0).size()-1; j++) {
                if (this.cityMap.get(i).get(j) == '.') {
                    this.countArea(i, j);
                }
            }
        }
    }

    public void countArea(int a, int b) {
        this.length = 1;
        this.width = 1;
        this.findLength(a, b);

        this.findWidth(a, b);
    }

    public void findLength(int a, int b) {
        if (this.cityMap.get(a).get(b+1) == '.') {
            this.length += 1;
            this.findLength(a, b+1);
        }
        return;
    }

    public void findWidth(int a, int b) {
        ArrayList<Character> minimalSquare = new ArrayList<>();
        for (int i = 0; i < this.length; i++) minimalSquare.add('.');
        ArrayList<Character> nextSquare = new ArrayList<>();
        for (int i = b; i < b + this.length; i++) nextSquare.add(this.cityMap.get(a+1).get(i));

        if (nextSquare.equals(minimalSquare)) {
            this.width += 1;
            if (this.length != 1) this.emptyAreas.add(this.length * this.width);
            if (a < this.cityMap.size()-1) this.findWidth(a+1, b);
        } else {
            if (this.length > 2) {
                this.length -= 1;
                this.findWidth(a, b);
            } else {return;}
        }

        return;

    }
    public static void main(String[] args) {
        
        PlayGround playGround = new PlayGround();
        playGround.giveMeData();



        // for (int i = 0; i < playGround.cityMap.size(); i++) {
        //     for (int j = 0; j < playGround.cityMap.get(0).size(); j++) {
        //         System.out.print(playGround.cityMap.get(i).get(j));
        //     }
        //     System.out.print("\n");
        // }
        // System.out.print("\n");


        playGround.findStart();

        if (playGround.emptyAreas.size() > 0) {
           System.out.println(Collections.max(playGround.emptyAreas)); 
        } else {
            System.out.println(0);
        }
      
    }
}

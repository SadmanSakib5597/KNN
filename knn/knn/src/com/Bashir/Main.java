package com.Bashir;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

class Iris implements Comparable<Iris>{

    double a;
    double b;
    double c;
    double d;
    double distance;
    String type;

    @Override
    public String toString()
    {
        return a + "," + b + "," + c + "," + d + "," + type + "," + distance;
    }

    @Override
    public int compareTo(Iris iris) {
        return (this.distance < iris.distance ? -1 :
                (this.distance == iris.distance ? 0 : 1));
    }
}

public class Main {
    private static final int A = 35;
    private static int n;
    private static int b=0;

    public static ArrayList<Iris> irises = new ArrayList<Iris>();
    public static Iris givenIris = new Iris();
    public static int K;

    public static void readDataset()
    {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\LENOVO\\Desktop\\knn\\knn\\src\\res"));

            while (sc.hasNext())
            {
                String line[] = sc.nextLine().split(",");

                Iris iris = new Iris();
                iris.a = Double.parseDouble(line[0]);
                iris.b = Double.parseDouble(line[1]);
                iris.c = Double.parseDouble(line[2]);
                iris.d = Double.parseDouble(line[3]);
                iris.type = line[4];

                irises.add(iris);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void inputGivenIris()
    {
        System.out.print("Enter Iris value (i.e>5.1,3.5,1.4,0.2,Type): ");
        Scanner sc = new Scanner(System.in);

        String line[] = sc.nextLine().split(",");

        Iris iris = new Iris();
        iris.a = Double.parseDouble(line[0]);
        iris.b = Double.parseDouble(line[1]);
        iris.c = Double.parseDouble(line[2]);
        iris.d = Double.parseDouble(line[3]);
        iris.type = line[4];

        givenIris.a = iris.a;
        givenIris.b = iris.b;
        givenIris.c = iris.c;
        givenIris.d = iris.d;
        givenIris.type = iris.type;
    }

    public static void calculateDistance()
    {
        for(int i=0; i<irises.size(); i++)
        {
            double distance = Math.sqrt(
                    (irises.get(i).a - givenIris.a)*(irises.get(i).a - givenIris.a) +
                            (irises.get(i).b - givenIris.b)*(irises.get(i).b - givenIris.b) +
                            (irises.get(i).c - givenIris.c)*(irises.get(i).c - givenIris.c) +
                            (irises.get(i).d - givenIris.d)*(irises.get(i).d - givenIris.d)
            );
            irises.get(i).distance = distance;
        }

        Collections.sort(irises);
    }

    public static void inputKvalue()
    {
        int k;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of K (K should be less than or equal " + irises.size() + "): " );

        do{
            k = sc.nextInt();
        }while(k>irises.size());

        K = k;
    }

    public static int getTypeCount(String type, int rangeA, int rangeB)
    {
        int count = 0;

        for(int i=rangeA; i<rangeB; i++)
        {
            if(irises.get(i).type.equals(type))
                count++;
        }

        return count;
    }

    public static double calculatePercentage(int itemCount, int totalItem)
    {
        return abs(((itemCount*100)/totalItem)-A);
    }

    public static Set<String> getTypes()
    {
        Set<String> types = new HashSet<>();

        for(int i=0; i<K; i++)
        {
            types.add(irises.get(i).type);
        }
        //System.out.println(types.size());
        return types;
    }

    public static void printType()
    {
        Set<String> types = getTypes();
        String[] typesArray = new String[types.size()];
        types.toArray(typesArray);

        for(int i=0; i<typesArray.length; i++)
        {
            int typeCount = getTypeCount(typesArray[i], 0, K);
            double percentage = calculatePercentage(typeCount, K);

            System.out.println("(probability of being)" + typesArray[i] + " = " + percentage+ "%");
        }
    }
    public static void takeInputFold()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Fold: ");
        int n = sc.nextInt();
        n = n;

        while(b!=0)
        {
            shuffle();
        }


    }

    public static void shuffle()
    {
        Set<String> myDataset = getTypes();
        for(String i:myDataset)
        {
            Random n = new Random(0);
            if(myDataset.contains(n.toString()))
            {
                int hash = myDataset.hashCode();
                if(hash!=myDataset.getClass().hashCode())
                {
                    myDataset.add(n.toString());
                }
            }
        }
    }
    public static void main(String[] args) {

        readDataset();
        inputGivenIris();
        takeInputFold();
        calculateDistance();
        inputKvalue();
        printType();

    }

}

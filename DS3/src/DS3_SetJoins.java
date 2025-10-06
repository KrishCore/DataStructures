import java.io.File;
import java.util.*;

public class DS3_SetJoins
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scan.next();
        System.out.println();
        DS3_Set<Integer> set1 = new DS3_Set<>();
        DS3_Set<Integer> set2 = new DS3_Set<>();

        DS3_Set<Integer> union = new DS3_Set<>();
        DS3_Set<Integer> intersection = new DS3_Set<>();

        DS3_Set<Integer> abSet = new DS3_Set<>();
        DS3_Set<Integer> baSet = new DS3_Set<>();

        try {
            File file = new File(fileName);
            Scanner fs = new Scanner(file);

            String line1 = fs.nextLine();
            Scanner ls = new Scanner(line1).useDelimiter(" ");
            while (ls.hasNext())
                set1.add(ls.nextInt());

            String line2 = fs.nextLine();
            Scanner ls2 = new Scanner(line2).useDelimiter(" ");
            while (ls2.hasNext())
                set2.add(ls2.nextInt());

            ArrayList<Integer> arr1 = new ArrayList<>(); // makes set A
            Iterator<Integer> it1 = set1.iterator();
            while (it1.hasNext())
                arr1.add(it1.next());

            ArrayList<Integer> arr2 = new ArrayList<>(); // makes set B
            Iterator<Integer> it2 = set2.iterator();
            while (it2.hasNext())
                arr2.add(it2.next());

            it1 = set1.iterator();
            while (it1.hasNext())
                union.add(it1.next()); //adds set A
            it2 = set2.iterator();
            while (it2.hasNext())
                union.add(it2.next()); //adds set B
            Iterator<Integer> uni = union.iterator();
            ArrayList<Integer> unionArr = new ArrayList<>(); // makes union
            while (uni.hasNext())
                unionArr.add(uni.next());
            Collections.sort(unionArr);

            it1 = set1.iterator(); //intersection starts
            while (it1.hasNext()) {
                Integer i1 = it1.next();
                if (set2.contains(i1))
                    intersection.add(i1);
            }
            ArrayList<Integer> inter = new ArrayList<>();
            Iterator<Integer> intr = intersection.iterator();
            while (intr.hasNext())
                inter.add(intr.next()); //adds not common items

            it1 = set1.iterator();
            while (it1.hasNext())
            {
                Integer i = it1.next();
                if (!set2.contains(i))
                    abSet.add(i);
            }
            Collections.sort(inter);
            ArrayList<Integer> abArr = new ArrayList<>(); // needs work
            Iterator<Integer> abIter = abSet.iterator();
            while (abIter.hasNext())
                abArr.add(abIter.next());

            it2 = set2.iterator();
            while (it2.hasNext())
            {
                Integer i = it2.next();
                if (!set1.contains(i))
                    baSet.add(i);
            }
            ArrayList<Integer> baArr = new ArrayList<>(); // needs work
            Iterator<Integer> baIter = baSet.iterator();
            while (baIter.hasNext())
                baArr.add(baIter.next());

            System.out.println("Set A: " + arr1);
            System.out.println("Set B: " + arr2);
            System.out.println();
            System.out.println("Union: " + unionArr);
            System.out.println("Intersection: " + inter);
            System.out.println("A - B (Elements in A not in B): " + abArr);
            System.out.println("B - A (Elements in B not in A): " + baArr);
        }
        catch (Exception e) {
//            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}

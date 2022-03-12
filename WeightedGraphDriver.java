import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Weighted Graph Driver
 *
 * @author Brian Huang
 * @version 11/19/2019
 */
public class WeightedGraphDriver
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner reader = new Scanner(System.in);

        System.out.println("Please input the name of the file including '.***'(.pat,.txt,etc.)");
        String name = reader.next();

        File ADTFile = new File(name);   
        Scanner ADT = new Scanner(ADTFile);
        String tempStringFile = ADT.nextLine();
        ArrayList<String> ADTArrayList = new ArrayList<>();
        String[] tempADTArray = tempStringFile.split(",",0);
        for(int i = 0; i < tempADTArray.length; i++)
        {
            ADTArrayList.add(tempADTArray[i]);
        }
        while(ADT.hasNext())
        {
            tempStringFile = ADT.nextLine();
            tempADTArray = tempStringFile.split(",",0);
            for(int i = 0; i < tempADTArray.length; i++)
            {
                ADTArrayList.add(tempADTArray[i]);
            }
        }

        LinkedList<Node> edgeList = new LinkedList<Node>();
        for(int i = 0; i < ADTArrayList.size(); i = i + 3)
        {
            try
            {
                Node tempEdge = new Node(ADTArrayList.get(i),ADTArrayList.get(i+1),Integer.parseInt(ADTArrayList.get(i+2)));
                edgeList.add(tempEdge);
            }
            catch(IndexOutOfBoundsException e)
            {
                Node tempEdge = new Node(ADTArrayList.get(i));
                edgeList.add(tempEdge);
                i = i -2;
            }
            catch(NumberFormatException e)
            {
                Node tempEdge = new Node(ADTArrayList.get(i));
                edgeList.add(tempEdge);
                i = i -2;
            }
        }

        WeightedGraph weightedGraph = new WeightedGraph(edgeList);
        System.out.println(".addEdge Expected: True");
        System.out.println(weightedGraph.addEdge("Bac Lieu","Darlac",2));
        System.out.println(".removeEdges Expected: True");
        System.out.println(weightedGraph.removeEdges("Ba Xuyen","Vinh Long"));
        weightedGraph.addVertex("B");
        System.out.println(".removeVertex Expected: True");
        System.out.println(weightedGraph.removeVertex("An Xuyen"));
        System.out.println(".contain(vertex) Expected: False");
        System.out.println(weightedGraph.contains("An Xuyen"));
        System.out.println(".contain(edge) Expected: False");
        System.out.println(weightedGraph.contains("Ba Xuyen","Vinh Long"));
        System.out.println(".heaviestCost() Expected: 2");
        System.out.println(weightedGraph.heaviestCost());
        System.out.println(".selfEdges() Expected: ");
        System.out.println(weightedGraph.selfEdges());
        System.out.println(".zeroInboundEdges() Expected: B");
        System.out.println(weightedGraph.zeroInboundEdges());
        System.out.println(".zeroOutboundEdges() Expected: B");
        System.out.println(weightedGraph.zeroOutboundEdges());
        System.out.println(".totalVerticies Expected: 44");
        System.out.println(weightedGraph.totalVerticies());
        
    }
}

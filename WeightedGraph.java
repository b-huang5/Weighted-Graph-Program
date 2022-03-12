import java.util.LinkedList;
import java.util.ArrayList;
/**
 * Weighted Graph Methods
 *
 * @author Brian Huang
 * @version 11/19/2019
 */
public class WeightedGraph
{
    LinkedList<Node> nodeGraph;
    public WeightedGraph(LinkedList<Node> graph)
    {
        nodeGraph = graph;
    }

    public boolean addEdge(String source, String dest, int cost)
    {
        boolean s = false;
        boolean d = false;
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getSource().equals(source) || (nodeGraph.get(i).getDestination() != null && nodeGraph.get(i).getDestination().equals(source))
            || (nodeGraph.get(i).getDestination() == null && nodeGraph.get(i).getDestination().equals(source)))
            {
                s = true;
            }
            if(nodeGraph.get(i).getSource().equals(dest) || (nodeGraph.get(i).getDestination() != null && nodeGraph.get(i).getDestination().equals(dest))
            || (nodeGraph.get(i).getDestination() == null && nodeGraph.get(i).getDestination().equals(dest)))
            {
                d = true;
            }
            if(s && d)
            {
                Node node = new Node(source,dest,cost);
                nodeGraph.add(node);
                return true;
            }
        }
        return false;
    }

    public boolean removeEdges(String source, String dest)
    {
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getSource().equals(source) && nodeGraph.get(i).getDestination().equals(dest))
            {
                nodeGraph.remove(i);
                Node node1 = new Node(source);
                Node node2 = new Node(dest);
                return true;
            }
        }
        return false;
    }

    public void addVertex(String name)
    {
        boolean temp = true;
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getSource().equals(name) && nodeGraph.get(i).getDestination() == null)
            {
                temp = false;
                break;
            }
        }
        if(temp)
        {
            Node edge = new Node(name);
            nodeGraph.add(edge);
        }
    }

    public boolean removeVertex(String name)
    {
        boolean temp = false;
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getDestination() != null && (nodeGraph.get(i).getSource().equals(name) && nodeGraph.get(i).getDestination().equals(name)))
            {
                nodeGraph.remove(i);
                i--;
                temp = true;
            }
            else if(nodeGraph.get(i).getSource().equals(name))
            {
                if(nodeGraph.get(i).getDestination() == null)
                {
                    nodeGraph.remove(i);
                    i--;
                    temp = true;
                }
                else
                {
                    Node node = new Node(nodeGraph.get(i).getDestination());
                    nodeGraph.remove(i);
                    i--;
                    nodeGraph.add(node);
                    temp = true;
                }
            }
            else if(nodeGraph.get(i).getDestination() != null && nodeGraph.get(i).getDestination().equals(name))
            {
                Node node = new Node(nodeGraph.get(i).getSource());
                nodeGraph.remove(i);
                i--;
                nodeGraph.add(node);
                temp = true;
            }
        }
        return temp;
    }

    public boolean contains(String name)
    {
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getSource().equals(name) || (nodeGraph.get(i).getDestination() != null && nodeGraph.get(i).getDestination().equals(name)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String source, String dest)
    {
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getSource().equals(source) && nodeGraph.get(i).getDestination().equals(dest))
            {
                return true;
            }
        }
        return false;
    }

    public int heaviestCost()
    {
        int max = nodeGraph.get(0).getCost();
        for(int i = 1; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getCost() > max)
            {
                max = nodeGraph.get(i).getCost();
            }
        }
        return max;
    }

    public String selfEdges()
    {
        String temp = "";
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            if(nodeGraph.get(i).getSource().equals(nodeGraph.get(i).getDestination()))
            {
                temp = temp + nodeGraph.get(i).getSource() + " ";
            }
        }
        return temp;
    }

    public String zeroInboundEdges()
    {
        String temp = "";
        ArrayList<String> inbound = new ArrayList<String>();
        ArrayList<String> outbound = new ArrayList<String>();
        ArrayList<String> duplicates = new ArrayList<String>();
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            outbound.add(nodeGraph.get(i).getSource());
            inbound.add(nodeGraph.get(i).getDestination());
        }
        for(int i = 0; i < outbound.size(); i++)
        {
            if(inbound.contains(outbound.get(i)) == false)
            {
                if(duplicates.contains(outbound.get(i)) == false)
                {
                    duplicates.add(outbound.get(i));
                    temp = temp + outbound.get(i) + " ";
                }
            }
        }
        return temp;
    }

    public String zeroOutboundEdges()
    {
        String temp = "";
        ArrayList<String> inbound = new ArrayList<String>();
        ArrayList<String> outbound = new ArrayList<String>();
        ArrayList<String> duplicates = new ArrayList<String>();
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            outbound.add(nodeGraph.get(i).getSource());
            inbound.add(nodeGraph.get(i).getDestination());
        }
        for(int i = 0; i < outbound.size(); i++)
        {
            if(outbound.contains(inbound.get(i)) == false)
            {
                if(duplicates.contains(inbound.get(i)) == false && inbound.get(i) != null)
                {
                    duplicates.add(inbound.get(i));
                    temp = temp + inbound.get(i) + " ";
                }
                else if(inbound.get(i) == null)
                {
                    String holder = outbound.get(i);
                    outbound.remove(i);
                    inbound.remove(i);
                    i--;
                    if(outbound.contains(holder) == false)
                    {
                        duplicates.add(holder);
                        temp = temp + holder + " ";
                    }
                }
            }
        }
        return temp;
    }

    public int totalVerticies()
    {
        ArrayList<String> verticies = new ArrayList<String>();
        ArrayList<String> duplicates = new ArrayList<String>();
        for(int i = 0; i < nodeGraph.size(); i++)
        {
            verticies.add(nodeGraph.get(i).getSource());
            verticies.add(nodeGraph.get(i).getDestination());
        }
        duplicates.add(verticies.get(0));
        for(int i = 1; i < verticies.size(); i++)
        {
            if(verticies.get(i) != null && duplicates.contains(verticies.get(i)) == false)
            {
                duplicates.add(verticies.get(i));
            }
        }
        return duplicates.size();
    }
}

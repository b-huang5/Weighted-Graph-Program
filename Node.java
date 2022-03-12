
/**
 * Node Object Class
 *
 * @author Brian Huang
 * @version 11/19/2019
 */
public class Node
{
    String source;
    String dest;
    int cost;
    public Node(String s)
    {
        source = s;
    }
    
    public Node(String s, String d, int c)
    {
        source = s;
        dest = d;
        cost = c;
    }
    
    public String getSource()
    {
        return source;
    }
    
    public String getDestination()
    {
        return dest;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public void setDestination(String d)
    {
        dest = d;
    }
    
    public void setCost(int c)
    {
        cost = c;
    }
}

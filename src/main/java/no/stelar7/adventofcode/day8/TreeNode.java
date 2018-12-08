package no.stelar7.adventofcode.day8;


import java.util.*;

public class TreeNode
{
    int            index;
    List<TreeNode> children = new ArrayList<>();
    List<Integer>  metadata = new ArrayList<>();
    
    public TreeNode(int indexVal, List<Integer> nodes)
    {
        this.index = indexVal;
        int childCount = nodes.get(index++);
        int metaCount  = nodes.get(index++);
        children.addAll(generateChildren(childCount, index, nodes));
        
        int end = index + metaCount;
        for (int i = index; i < end; i++)
        {
            metadata.add(nodes.get(i));
            index++;
        }
    }
    
    private List<TreeNode> generateChildren(int count, int index, List<Integer> nodes)
    {
        List<TreeNode> vals = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            TreeNode node = new TreeNode(this.index, nodes);
            this.index = node.index;
            vals.add(node);
        }
        return vals;
    }
    
    public int values()
    {
        int valueT = 0;
        
        for (Integer meta : metadata)
        {
            valueT += meta;
        }
        
        for (TreeNode child : children)
        {
            valueT += child.values();
        }
        
        return valueT;
    }
    
    public int valuesIndexed()
    {
        int valueT = 0;
        if (children.size() == 0)
        {
            return values();
        }
        
        for (Integer meta : metadata)
        {
            if (children.size() < meta)
            {
                continue;
            }
            
            valueT += children.get(meta - 1).valuesIndexed();
        }
        
        return valueT;
    }
}

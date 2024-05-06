/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travelmap.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fer
 */
public class BTreeNode {
     private boolean leaf;
    private List<Double> keys;
    private List<BTreeNode> children;

    public BTreeNode() {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.leaf = true;

    }

    public List<Double> getKeys() {
        return keys;
    }

    public List<BTreeNode> getChildren() {
        return children;
    }
        public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
    
}

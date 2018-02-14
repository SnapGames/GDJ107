/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2018
 */
package com.snapgames.gdj.core.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Node structure management with horizontal and vertical structure.
 * 
 * @author Frédéric Delorme
 *
 */
public class Node {
	/**
	 * the full list of create nodes.
	 */
	private static Map<String, Node> nodes = new ConcurrentHashMap<>();
	/**
	 * this node name.
	 */
	private String name;

	/**
	 * the root node
	 */
	private static Node rootNode;

	/**
	 * the previous node in the horizontal chain.
	 */
	private Node prev;
	/**
	 * the next node in the horizontal chain.
	 */
	private Node next;
	/**
	 * the previous node in the vertical chain
	 */
	private Node parent;
	/**
	 * the next in the vertical chain.
	 */
	private Node child;

	/**
	 * create a new NODE with a <code>name</code>.
	 * 
	 * @param name
	 */
	public Node(String name) {
		assert (!nodes.containsKey(name));
		this.name = name;
	}

	public void setRoot(Node rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * Add a <code>node</code> to the current node.
	 * 
	 * @param node
	 */
	public void add(Node node) {
		assert (rootNode != null);
		assert (node != null);

		// if root exist, search for next available slot.
		Node index = rootNode;
		while (index.next != null) {
			index = index.next;
		}
		index.next = node;
		node.prev = index;
	}

	/**
	 * Add a child <code>node</code>
	 * 
	 * @param node
	 */
	public void addChild(Node node) {
		assert (node != null);
		if (this.child == null) {
			this.child = node;
			node.parent = this;
		} else {
			this.child.add(node);
			node.parent = this;
		}
	}

	/**
	 * retrieve a node on its <code>name</code> from the Nodes tree.
	 * 
	 * @param name
	 *            name of the node to retrieve.
	 * @return
	 */
	public Node getNode(String name) {
		assert (nodes.containsKey(name));
		return nodes.get(name);
	}

	/**
	 * remove the <code>name</code>'s node from the tree.
	 * 
	 * @param node
	 */
	public void removeNode(String name) {
		Node n = nodes.get(name);
		// TODO update prev,next,parent,child according to the node removing.
	}

	public Node next() {
		return next;

	}

	public Node previous() {
		return prev;
	}

	public Node parent() {
		return parent;
	}

	public Node child() {
		return child;
	}
}

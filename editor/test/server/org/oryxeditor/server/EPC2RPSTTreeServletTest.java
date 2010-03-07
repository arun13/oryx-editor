/*please put rpst.jar to your library pass
 * put ogdf.dll to C:/Windows/System32/
 * give "-Djava.library.path=C:/Windows/System32/" as VM Argument in Run Configurations*/


package oryx.editor.server.src.org.oryxeditor.server;



import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.oryxeditor.server.EPC2RPSTTreeServlet;

import de.bpt.hpi.graph.Graph;
import de.bpt.hpi.ogdf.rpst.RPST;
import de.bpt.hpi.ogdf.spqr.TreeNode;

public class EPC2RPSTTreeServletTest extends TestCase {
	EPC2RPSTTreeServlet epc2rpst = new EPC2RPSTTreeServlet();
	Graph g = this.createGraph();
	RPST rpst = new RPST(g);
	Map<Integer, String> rpstIdJsonId = new HashMap<Integer, String>();
	
	
	public void constructTest() {
		epc2rpst.setRpst(rpst);
		rpst = epc2rpst.getRpst();
		epc2rpst.setRpstId2JsonId(this.createVertexDictionary());
		this.createJsonResponse();
	}


	public void testDepth() {
		constructTest();
		TreeNode root = rpst.getRootNode();
		int treeDepth = epc2rpst.getDepth(rpst);
		int rootTreeDepth = epc2rpst.getDepth(rpst, root);
		int treeDepthTest = this.getDepth();
		assertEquals(treeDepth, treeDepthTest);
		assertTrue(rootTreeDepth == 0);
		for (TreeNode node : rpst.getNodes()) {
			int nodeDepth = epc2rpst.getDepth(rpst, node);
			if (nodeDepth != 0) {
				int parentDepth = epc2rpst.getDepth(rpst, node.getParentNode());
				assertTrue(nodeDepth - 1 == parentDepth);
			}

		}
	}

	public void testRootParent() {
		constructTest();
		for (TreeNode node : rpst.getNodes()) {
			if (node == rpst.getRootNode()) {
				assertNull(node.getParentNode());
			}
		}
	}
	
	
	public void testResponse(){
		constructTest();
		JSONArray servletResponse = epc2rpst.createJsonResponse(rpst);
		JSONArray testResponse = this.createJsonResponse();
		assertNotNull(servletResponse);
		assertEquals(servletResponse.toString(), testResponse.toString());
	}

	public void testRpstNode2Json() {
		constructTest();
	
			try {
				for(TreeNode node: rpst.getNodes()){ 
					String jsonNodeExit = epc2rpst.rpstNode2JSON(rpst, node).getString("nodeExit");
					String jsonNodeEntry = epc2rpst.rpstNode2JSON(rpst, node).getString("nodeEntry");
					String jsonNodeExitTest = "id: " + rpst.getExit(node).toString();
					String jsonNodeEntryTest = "id: " + rpst.getEntry(node).toString();
					
					assertEquals(jsonNodeExit, jsonNodeExitTest );
					assertEquals(jsonNodeEntry, jsonNodeEntryTest );
					
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
			
			
	// checks whether json consists all children of the root
			@SuppressWarnings("unchecked")
			public void testRootChildren (){
				constructTest();
				TreeNode root = rpst.getRootNode();
				Vector children = new Vector();
				try {
					
					for(TreeNode node: root.getChildNodes()){
						// order can be different
						children.add(rpst.getEntry(node).toString().concat(rpst.getExit(node).toString()));
						children.add(rpst.getExit(node).toString().concat(rpst.getEntry(node).toString()));
					}
					
						int length = epc2rpst.childNodes2JSON(rpst, root).length();
						//System.out.println(length);
						for(int i = 0; i < length; i++){
							String exit = epc2rpst.childNodes2JSON(rpst, root).getJSONObject(i).getString("nodeExit").substring(4);
							String entry = epc2rpst.childNodes2JSON(rpst, root).getJSONObject(i).getString("nodeEntry").substring(4);
							assertTrue(children.contains(exit.concat(entry)));
					//		System.out.println("exit: "+exit);
					//		System.out.println("entry: "+entry);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	
	}
			// checks whether json consists all children of the each node
			public void testNodeChildren (){
				constructTest();
				TreeNode root = rpst.getRootNode();
				Vector children;
				for(TreeNode node: root.getChildNodes()){
					children = new Vector();
					for(TreeNode child: node.getChildNodes()){
						// order can be different
					children.add(rpst.getEntry(child).toString().concat(rpst.getExit(child).toString()));
					children.add(rpst.getExit(child).toString().concat(rpst.getEntry(child).toString()));
				}
					//System.out.println(node);
					//System.out.println(children);
	
					int length = epc2rpst.childNodes2JSON(rpst, node).length();
				//	System.out.println(epc2rpst.childNodes2JSON(rpst, node));
				//	System.out.println(length);
					for(int i = 0; i < length; i++){
						String exit;
						try {
							exit = epc2rpst.childNodes2JSON(rpst, node).getJSONObject(i).getString("nodeExit").substring(4);
							String entry = epc2rpst.childNodes2JSON(rpst, node).getJSONObject(i).getString("nodeEntry").substring(4);
							assertTrue(children.contains(exit.concat(entry)));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
				}
	}
					
	}

	Graph createGraph() {
		Graph g = new Graph();
		Integer n1 = g.addVertex();
		Integer n2 = g.addVertex();
		Integer n3 = g.addVertex();
		Integer n4 = g.addVertex();
		Integer n5 = g.addVertex();
		Integer n6 = g.addVertex();
		Integer n7 = g.addVertex();
		Integer n8 = g.addVertex();
		Integer n9 = g.addVertex();
		Integer n10 = g.addVertex();
		Integer n11 = g.addVertex();
		Integer n12 = g.addVertex();
		Integer n13 = g.addVertex();
		Integer n14 = g.addVertex();
		Integer n15 = g.addVertex();
		Integer n19 = g.addVertex();
		Integer n16 = g.addVertex();
		Integer n17 = g.addVertex();
		Integer n18 = g.addVertex();

		g.addEdge(n1, n2);
		g.addEdge(n1, n3);
		g.addEdge(n3, n4);
		g.addEdge(n2, n4);
		g.addEdge(n3, n5);
		g.addEdge(n4, n6);
		g.addEdge(n5, n6);
		g.addEdge(n5, n7);
		g.addEdge(n7, n8);
		g.addEdge(n6, n8);
		g.addEdge(n4, n9);
		g.addEdge(n8, n9);
		g.addEdge(n9, n10);
		g.addEdge(n10, n11);
		g.addEdge(n10, n12);
		g.addEdge(n11, n13);
		g.addEdge(n12, n13);
		g.addEdge(n15, n1);
		g.addEdge(n13, n14);
		g.addEdge(n10, n19);
		g.addEdge(n10, n17);
		g.addEdge(n19, n16);
		g.addEdge(n17, n18);
		g.addEdge(n16, n12);
		g.addEdge(n18, n12);

		return g;
	}

	Map<Integer, String> createVertexDictionary() {
		for (TreeNode node : rpst.getNodes()) {
			rpstIdJsonId.put(rpst.getExit(node), "id: "+rpst.getExit(node));
			rpstIdJsonId.put(rpst.getEntry(node), "id: "+rpst.getEntry(node));
		}

		return rpstIdJsonId;
	}

	public JSONArray createJsonResponse() {
		JSONArray jsonResponse = new JSONArray() ;
		JSONObject treeDepth = new JSONObject();
		TreeNode root = rpst.getRootNode();
		try {
			treeDepth = treeDepth.put("treeDepth", getDepth());
			// parse Tree
			JSONObject jsonRoot = node2JSON(root);
			jsonResponse.put(jsonRoot);
			jsonResponse.put(treeDepth);
			
		} catch (JSONException e) {
			System.out.println("jsonError" + e);
		}

		return jsonResponse;
	}

	// Node -> JSON

	public JSONObject node2JSON(TreeNode node) {
		JSONObject json = new JSONObject();
		rpstIdJsonId = this.createVertexDictionary();
		String jsonTitleIncoming = "nodeEntry";
		String jsonTitleOutgoing = "nodeExit";
		String jsonTitleType = "type";
		
		String jsonValueIncoming = rpstIdJsonId.get(rpst.getEntry(node))
		.toString();
		String jsonValueOutgoing = rpstIdJsonId.get(rpst.getExit(node))
		.toString();
		String jsonValueType = node.getNodeType().toString();
		try {
			// Root in json
			json.put(jsonTitleIncoming, jsonValueIncoming);
			json.put(jsonTitleOutgoing, jsonValueOutgoing);
			json.put("depth", new Integer(this.getDepth(node)).toString());
			json.put(jsonTitleType, jsonValueType);
			// if exists, get childNodes,
			if (node.getChildNodes().size() != 0) {
				json.put("children", childNodes2JSON(node));
			}
			

		} catch (JSONException e) {

			System.out.println("Addition of JSONnode failed: " + e);
		}

		return json;

	}

	// adds ChildNodes of a parent Node to JSONArray
	public JSONArray childNodes2JSON(TreeNode parent) {
		JSONArray jsonChildren = new JSONArray();

		for (TreeNode childNode : parent.getChildNodes()) {
			jsonChildren.put(node2JSON(childNode));
		}
		return jsonChildren;

	}

	// returns depth of the Tree
	public int getDepth() {
		int maxDepth = 0;
		for (TreeNode node : rpst.getNodes()) {
			if (getDepth(node) > maxDepth) {
				maxDepth = getDepth(node);
			}
		}
		return maxDepth;
	}

	// returns depth of the node
	public int getDepth(TreeNode node) {
		if (node == rpst.getRootNode()) {
			return 0;
		}
		return getDepth(node.getParentNode()) + 1;
	}
}

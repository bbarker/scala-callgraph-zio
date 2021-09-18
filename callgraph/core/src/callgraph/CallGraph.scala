package callgraph

trait CallGraphEvaluator

enum Graph:
  case Node(name: String, typeName: String)
  case Edge(source: Node, target: Node, edgeType: String)

import Graph.*

trait CallGraph(val metaData: String /* TODO Not just String*/ /*,  root: Node */ )
// TODO - This should hold a graph-like data structure: Graph

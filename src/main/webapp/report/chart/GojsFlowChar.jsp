<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>在线公司流程图</title>
<!--引入公共js和样式文件-->
<%-- <%@ include file="/common/style.jsp"%> --%>
<script type="text/javascript" src="jsLib/QUI/libs/js/jquery.js"></script>
<script type="text/javascript" src="jsLib/gojs/flow/layer/layer.js"></script>
<script type="text/javascript" src="jsLib/gojs/flow/go.js"></script>
<script type="text/javascript" src="jsLib/gojs/flow/flow-desinger.js"></script>
<script type="text/javascript" src="jsLib/gojs/flow/flow-display.js"></script>
<script type="text/javascript" src="jsLib/gojs/flow/goSamples.js"></script>
<link href="jsLib/gojs/flow/goSamples.css" rel="stylesheet" type="text/css"/>
<script id="code">
window.onload = function getFlow(){
	var actionUrl="flow!GoJsXml.action";
	$.ajax({
		type : "post",
		url : actionUrl,
		async : true,
		success : function(result) {
			init(result);
		},
		beforeSend : function(request) {
		},
		error : function(json) {
		}
	}); 
};



  function init(result) {
    if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
    var $ = go.GraphObject.make;  // for conciseness in defining templates
    myDiagram = $(go.Diagram, "myDiagramDiv",  // create a Diagram for the DIV HTML element
                  {
                    initialContentAlignment: go.Spot.Center,  // center the content
                    "undoManager.isEnabled": true  // enable undo & redo
                  });
    // define a simple Node template
   	 myDiagram.nodeTemplate =
      $("Node", "Auto",
        { locationSpot: go.Spot.Center,
          layerName: "Background" },  // always have regular nodes behind Links
        new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
        $("Shape", "RoundedRectangle",
          { fill: "white", stroke: null,
            portId: "", fromLinkable: true, toLinkable: true, cursor: "pointer" },
          new go.Binding("fill")),
        $("TextBlock",
          { margin: 8 , maxSize:new go.Size(120, NaN), textAlign:"center", font:"8pt Helvetica, Arial, sans-serif"},  // make some extra space for the shape around the text
          new go.Binding("text"))  // the label shows the node data's key
      );
    
      myDiagram.linkTemplate =
					$(go.Link, // the whole link panel
						{
							routing: go.Link.AvoidsNodes,
							curve: go.Link.JumpOver,
							corner: 5,
							toShortLength: 4,
							relinkableFrom: true,
							relinkableTo: true,
							reshapable: true,//允许拖动线
							resegmentable: true,//允许新增拐点
							// mouse-overs subtly highlight links:
							mouseEnter: function(e, link) {
								link.findObject("HIGHLIGHT").stroke = "rgba(30,144,255,0.2)";
							},
							mouseLeave: function(e, link) {
								link.findObject("HIGHLIGHT").stroke = "transparent";
							}
						},
						new go.Binding("points").makeTwoWay(),
						$(go.Shape, // the highlight shape, normally transparent
							{
								isPanelMain: true,
								strokeWidth: 8,
								stroke: "transparent",
								name: "HIGHLIGHT"
							}),
						$(go.Shape, // the link path shape
							{
								isPanelMain: true,
								stroke: "gray",
								strokeWidth: 2
							}),
						$(go.Shape, // the arrowhead
							{
								toArrow: "standard",
								stroke: null,
								fill: "gray"
							}),
						$(go.Panel, "Auto", // the link label, normally not visible
							{
								visible: false,
								name: "LABEL",
								segmentIndex: 2,
								segmentFraction: 0.5
							},
							new go.Binding("visible", "visible").makeTwoWay(),
							$(go.Shape, "RoundedRectangle", // the label shape
								{
									fill: "#F8F8F8",
									stroke: null
								}),
							$(go.TextBlock, "Yes", // the label
								{
									textAlign: "center",
									font: "10pt helvetica, arial, sans-serif",
									stroke: "#333333",
									editable: true
								},
								new go.Binding("text", "text").makeTwoWay())
						)
					);
	    // but use the default Link template, by not setting Diagram.linkTemplate
	    // create the model data that will be represented by Nodes and Links
		model = myDiagram.model.toJson();
		myDiagram.isModified = false;
	    myDiagram.model = go.Model.fromJson(result); 
  }
</script>
</head>
<body>
<div id="sample" style="width: 100%; hight:100%; margin: 0 auto">
	<!-- 图例 -->
	<div style="padding: 20px 20px 20px 20px;" align="center">
		<span style="display: inline-block; height: 12px; width: 12px; background: #4fba4f; margin-left: 6px; vertical-align: middle;"></span>
			<label style="vertical-align: middle;">已完成步骤</label> 
		<span style="display: inline-block; height: 12px; width: 12px; background: #ff9001; margin-left: 6px; vertical-align: middle;"></span>
			<label style="vertical-align: middle;">待处理步骤</label>
		<span style="display: inline-block; height: 12px; width: 12px; background: #7e7e7f; margin-left: 6px; vertical-align: middle;"></span>
			<label style="vertical-align: middle;">未经过步骤</label>
	</div>
	<div style="width: 100%; white-space: nowrap;" id="sample">
		<!--  显示面板 -->
		<span style="display: inline-block; vertical-align: top; padding: 10px; width: 80%">
			<div id="myDiagramDiv" style="border: solid 1px #7e7e7f; height: 520px"></div>
		</span>
	</div>
</div>
</body>
</html>
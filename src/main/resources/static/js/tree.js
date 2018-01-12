/**
 * 加载树节点
 * @param document
 * @param flag 是否异步加载
 * @param url
 * @returns
 */
function initTree(t){
//	zTreeNodes=[{"name":rootNodeName,isParent:true,open:true}];
	var treeObj=$("#"+t.id);
	if(t.search){
		treeObj.before("<input id=\"treeSearch\" type=\"text\" placeholder=\"请输入搜索内容\" onkeypress=\"treeSearch('"+t.id+"',this,event)\"/>");
	}
	var async;
	if(t.async){
		async={
                enable: true,
                url:t.url,
                autoParam:["id", "name", "path"]
            };
	}else{
		async={enable: false}
	}
	var setting = {
			data: {  
                simpleData: {  
                    enable: true,
                    idKey: "id",
        			pIdKey: "pid",
                    rootPId: null
                }  
            },
            async:async,
            view:{
            	selectedMulti: false
            },
            callback:{
            	onClick:zTreeClick
            }
	 };
	var zTree=$.fn.zTree.init(treeObj, setting,t.zTreeNodes);
	return zTree;
}

/**
 * 根据treeName进行搜索
 * @param document
 */
var allTreeNodes,nodeList,flag=true;
function treeSearch(id,search,event){
	if(event.which==13){
		var param=$(search).val();
		if(flag){
			allTreeNodes=$.fn.zTree.getZTreeObj(id).getNodes();
			flag=false;
		}
		if(param==""){
			nodeList=allTreeNodes;
		}else{
			nodeList = zTree.getNodesByParamFuzzy("name", param);
		}
		initTree({
			id:id,
			async:false,
			zTreeNodes:nodeList,
			search:false
		});
	}
}

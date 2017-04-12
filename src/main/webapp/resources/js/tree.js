function showTree(document,url,param){
	var setting = {
			 data: {  
                simpleData: {  
                    enable: true  
                }  
            },
            async: {
                enable: true,
                url:url,
                autoParam:["id", "name", "path"],
                otherParam:param
            },
            callback:{
            	onClick:zTreeOnClick
            }
	 };
	var zTree=$.fn.zTree.init(document, setting);
	return zTree;
}
function zTreeOnClick(event, treeId, treeNode){
	
}
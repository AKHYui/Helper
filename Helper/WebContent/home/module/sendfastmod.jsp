<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="sendfastmod" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">快速求助</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <!-- enctype="multipart/form-data" -->
		<form action="GuestSendFastMod" method="get" class="form-horizontal">
		<div class="form-group">
		
    	<label for="text" class="control-label col-sm-4 col-sm-offset-1"><i class="fa fa-tag"></i>&nbsp;求&nbsp;助:</label>
    	<input type="text" class="form-control" name="text" placeholder="至少5个字符" id="text" value="" ><br/>
    	<label for="phone" class="control-label col-sm-4 col-sm-offset-1"><i class="fa fa-mobile-phone"></i>&nbsp;联系方式:</label>
    	<input type="text" class="form-control" name="phone" placeholder="请输入有效的手机号码" id="phone" value=""><br/>
    	<label for="money" class="control-label col-sm-4 col-sm-offset-1"><i class="fa fa-mobile-phone"></i>&nbsp;酬&nbsp;金:</label>
    	<input type="text" class="form-control" name="money" placeholder="请输入有效的数字" id="money" value=""><br/>
      	</div>
      	<div class="modal-footer">
      	<input type="submit" value="提交" class="btn btn-primary"></input>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        </div>
		</form>
      </div>
      </div>
    </div>
  </div>
<!-- 查看信息用的模态框 结束 -->

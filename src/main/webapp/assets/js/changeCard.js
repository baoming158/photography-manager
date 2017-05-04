$('#changeImg').bind('change',function(){
	var $this = $(this),$img=$('#positionImg');
	$img.attr('src','assets/images/img_'+$this.val()+'.jpg');
});
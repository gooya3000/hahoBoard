$(function(){
  $(document).ready(function(){
      $('#welcome').slideDown(500);
  });
  
  
  $('.hamburger').click(function(){
 
      $('.hamburger').toggleClass('active');
      $('#side_nav').toggleClass('active');
      
  });
    
    
  /*  
  $('#postDelBtn').click(function(){
  
  	$('.modal').css('display','block');
  
  });
  */
  $('.cancle_box').click(function(){
  
  	window.history.back();
  
  });
  
  
});

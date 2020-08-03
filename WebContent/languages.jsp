<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$.ajax({
		url : 'LanguagesDao',
		data : {
			userName : $('#button').val()
		},
		success : function(responseText) {
			$('#langs').html(responseText);
		}
	});
});
</script>
<script>

$('#button').click(function() {
	var testval = [];
	 $('.lang_class:checked').each(function() {
	   testval.push($(this).val());
	 });
    $.ajax({
      type: "GET",
      url: "LanguageDao",
      data: {
        para:testval
      },
      success: function(result) {
        alert('ok');
      },
      error: function(result) {
        alert('error');
      }
    });
});

</script>
</head>
<body>
<div id="langs"></div>
<input type="submit" id="button" value="Languages"/>
<div id="ajaxGetUserServletResponse"></div>
<p id="pp"></p>
</body>
</html>


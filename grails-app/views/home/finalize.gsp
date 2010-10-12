<meta name="layout" content="app"></meta>
<script type="text/javascript" charset="utf-8">
$(function() {
  var intervalId = setInterval(function() {
    $.ajax({
      url: '<%= request.contextPath %>/poll?rnd=' + Math.floor(Math.random() * 2048) + '&jobUrl=<%= videoUrl %>',
      dataType: 'json',
      success: function(data) {
        if (data.completed) {
          window.location = data.url;
        }
        else {
          $('#status').html($('#status').html() + ".");
        }
      },
      error: function() {
        alert('Oh no, something went wrong.');
        clearInterval(intervalId);
      }
    });
  }, 5000);
});
</script>
<h1>Your Animoto video is rendering...</h1>
<p>Just hang out and we'll show your video soon. Maybe grab an O'Douls from Bar Web2.</p>
<div id="status">
  Status: Rendering
</div>

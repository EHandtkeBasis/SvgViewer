
function enableSvgPan(svgContainerId) {
  var el = document.getElementById(svgContainerId);
  if (!el) return;
  var isDown = false, startX, startY, scrollLeft, scrollTop;
  el.style.cursor = 'grab';
  el.addEventListener('mousedown', function(e) {
    isDown = true;
    el.style.cursor = 'grabbing';
    startX = e.pageX - el.offsetLeft;
    startY = e.pageY - el.offsetTop;
    scrollLeft = el.parentElement.scrollLeft;
    scrollTop = el.parentElement.scrollTop;
  });
  el.addEventListener('mouseleave', function() {
    isDown = false;
    el.style.cursor = 'grab';
  });
  el.addEventListener('mouseup', function() {
    isDown = false;
    el.style.cursor = 'grab';
  });
  el.addEventListener('mousemove', function(e) {
    if (!isDown) return;
    e.preventDefault();
    var x = e.pageX - el.offsetLeft;
    var y = e.pageY - el.offsetTop;
    el.parentElement.scrollLeft = scrollLeft - (x - startX);
    el.parentElement.scrollTop = scrollTop - (y - startY);
  });
}

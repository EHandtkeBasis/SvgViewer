

function enableSvgPan() {
  var el = document.getElementsByClassName('svg-inner')[0];
  if (!el) return;
  var isDown = false, startX, startY, scrollLeft, scrollTop;
  var holdTimer = null;
  el.addEventListener('mousedown', function(e) {
    isDown = true;
    holdTimer = setTimeout(function() {
      if (isDown) {
        el.style.cursor = 'grabbing';
      }
    }, 300);
    startX = e.pageX - el.offsetLeft;
    startY = e.pageY - el.offsetTop;
    scrollLeft = el.parentElement.scrollLeft;
    scrollTop = el.parentElement.scrollTop;
  });
  el.addEventListener('mouseleave', function() {
    isDown = false;
    clearTimeout(holdTimer);
    el.style.cursor = 'auto';
  });
  el.addEventListener('mouseup', function() {
    isDown = false;
    clearTimeout(holdTimer);
    el.style.cursor = 'auto';
  });
  el.addEventListener('mousemove', function(e) {
    if (!isDown || el.style.cursor !== 'grabbing') return;
    e.preventDefault();
    var x = e.pageX - el.offsetLeft;
    var y = e.pageY - el.offsetTop;
    el.parentElement.scrollLeft = scrollLeft - (x - startX);
    el.parentElement.scrollTop = scrollTop - (y - startY);
  });
}
enableSvgPan();

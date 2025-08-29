

function enableSvgPan() {
  var el = document.getElementsByClassName('svg-inner')[0];
  if (!el) return;
  var contentEl = document.getElementsByClassName('svg-viewer__content')[0];
  if (!contentEl) return;
  
  var isDown = false, startX, startY, scrollLeft, scrollTop;
  var holdTimer = null;
  
  el.addEventListener('mousedown', function(e) {
    isDown = true;
    holdTimer = setTimeout(function() {
      if (isDown) {
        el.style.cursor = 'grabbing';
      }
    }, 300);
    startX = e.pageX;
    startY = e.pageY;
    scrollLeft = contentEl.scrollLeft;
    scrollTop = contentEl.scrollTop;
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
    var walkX = (e.pageX - startX) * 2;
    var walkY = (e.pageY - startY) * 2;
    contentEl.scrollLeft = scrollLeft - walkX;
    contentEl.scrollTop = scrollTop - walkY;
  });
}
enableSvgPan();

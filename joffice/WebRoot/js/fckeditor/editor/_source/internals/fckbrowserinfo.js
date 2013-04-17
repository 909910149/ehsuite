var s=navigator.userAgent.toLowerCase();var FCKBrowserInfo={IsIE:
/*@cc_on!@*/
false,IsIE7:
/*@cc_on!@*/
false&&(parseInt(s.match(/msie (\d+)/)[1],10)>=7),IsIE6:
/*@cc_on!@*/
false&&(parseInt(s.match(/msie (\d+)/)[1],10)>=6),IsSafari:s.Contains(" applewebkit/"),IsOpera:!!window.opera,IsAIR:s.Contains(" adobeair/"),IsMac:s.Contains("macintosh")};(function(e){e.IsGecko=(navigator.product=="Gecko")&&!e.IsSafari&&!e.IsOpera;e.IsGeckoLike=(e.IsGecko||e.IsSafari||e.IsOpera);if(e.IsGecko){var d=s.match(/rv:(\d+\.\d+)/);var f=d&&parseFloat(d[1]);if(f){e.IsGecko10=(f<1.8);e.IsGecko19=(f>1.8);}}if(e.IsSafari){e.IsSafari3=(parseFloat(s.match(/ applewebkit\/(\d+)/)[1])<526);}})(FCKBrowserInfo);
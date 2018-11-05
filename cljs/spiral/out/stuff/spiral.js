// Compiled by ClojureScript 1.9.293 {}
goog.provide('stuff.spiral');
goog.require('cljs.core');
goog.require('quil.core');
stuff.spiral.draw = (function stuff$spiral$draw(){
var t = (quil.core.frame_count.call(null) / (10));
var c = quil.core.map_range.call(null,quil.core.cos.call(null,t),(-1),(1),(0),(1));
var w2 = (quil.core.width.call(null) / (2));
var h2 = (quil.core.height.call(null) / (2));
var vec__8217 = stuff.spiral.f.call(null,t);
var x = cljs.core.nth.call(null,vec__8217,(0),null);
var y = cljs.core.nth.call(null,vec__8217,(1),null);
var tr__8120__auto__ = new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [w2,h2], null);
quil.core.push_matrix.call(null);

try{quil.core.translate.call(null,tr__8120__auto__);

quil.core.stroke.call(null,c,(1),(1));

return quil.core.point.call(null,x,y);
}finally {quil.core.pop_matrix.call(null);
}});

// Compiled by ClojureScript 1.9.473 {}
goog.provide('fractal_trees.core');
goog.require('cljs.core');
goog.require('quil.core');
goog.require('quil.middleware');
fractal_trees.core.maxDepth = (14);
fractal_trees.core.dl = 0.66;
fractal_trees.core.setup = (function fractal_trees$core$setup(){
quil.core.frame_rate.call(null,(30));

quil.core.fill.call(null,(0));

quil.core.no_loop.call(null);

quil.core.color_mode.call(null,new cljs.core.Keyword(null,"hsb","hsb",-753472031));

return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"angle","angle",1622094254),6.0], null);
});
fractal_trees.core.update_state = (function fractal_trees$core$update_state(state){
return state;
});
fractal_trees.core.branch = (function fractal_trees$core$branch(len,width,depth,angle){
quil.core.stroke_weight.call(null,width);

quil.core.ellipse.call(null,(0),(0),(len / fractal_trees.core.dl),(len / fractal_trees.core.dl));

quil.core.translate.call(null,(0),(- len));

if((depth < fractal_trees.core.maxDepth)){
quil.core.push_matrix.call(null);

quil.core.rotate.call(null,angle);

fractal_trees.core.branch.call(null,(len * fractal_trees.core.dl),(width * fractal_trees.core.dl),(depth + (1)),angle);

quil.core.pop_matrix.call(null);

quil.core.push_matrix.call(null);

quil.core.rotate.call(null,(- (angle * 0.2)));

fractal_trees.core.branch.call(null,(len * fractal_trees.core.dl),(width * fractal_trees.core.dl),(depth + (1)),angle);

return quil.core.pop_matrix.call(null);
} else {
return null;
}
});
fractal_trees.core.draw_state = (function fractal_trees$core$draw_state(state){
var w = quil.core.width.call(null);
var h = quil.core.height.call(null);
quil.core.background.call(null,(51));

quil.core.stroke.call(null,(255));

quil.core.translate.call(null,(w * 
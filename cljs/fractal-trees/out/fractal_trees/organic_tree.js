// Compiled by ClojureScript 1.9.473 {}
goog.provide('fractal_trees.organic_tree');
goog.require('cljs.core');
goog.require('quil.core');
goog.require('quil.middleware');
fractal_trees.organic_tree.maxDepth = (10);
fractal_trees.organic_tree.dl = 0.8;
fractal_trees.organic_tree.setup = (function fractal_trees$organic_tree$setup(){
quil.core.frame_rate.call(null,(30));

quil.core.fill.call(null,(0));

quil.core.color_mode.call(null,new cljs.core.Keyword(null,"hsb","hsb",-753472031));

return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"angle","angle",1622094254),(0)], null);
});
fractal_trees.organic_tree.update_state = (function fractal_trees$organic_tree$update_state(state){
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"angle","angle",1622094254),(new cljs.core.Keyword(null,"angle","angle",1622094254).cljs$core$IFn$_invoke$arity$1(state) + 0.01)], null);
});
fractal_trees.organic_tree.branch = (function fractal_trees$organic_tree$branch(len,width,depth,angle){
quil.core.stroke_weight.call(null,width);

quil.core.line.call(null,(0),(0),(0),(- len));

quil.core.translate.call(null,(0),(- len));

if((depth < fractal_trees.organic_tree.maxDepth)){
quil.core.push_matrix.call(null);

quil.core.rotate.call(null,angle);

fractal_trees.organic_tree.branch.call(null,(len * fractal_trees.organic_tree.dl),(width * fractal_trees.organic_tree.dl),(depth + (1)),angle);

quil.core.pop_matrix.call(null);

quil.core.push_matrix.call(null);

quil.core.rotate.call(null,(- (angle * 0.2)));

fractal_trees.organic_tree.branch.call(null,(len * fractal_trees.organic_tree.dl),(width * fractal_trees.organic_tree.dl),(depth + (1)),angle);

return quil.core.pop_matrix.call(null);
} else {
return null;
}
});
fractal_trees.organic_tree.draw_state = (function fractal_trees$organic_tree$draw_state(state){
quil.core.background.call(null,(51));

quil.core.stroke.call(null,(255));

quil.core.translate.call(null,(quil.core.width.call(null) / (2)),quil.core.height.call(null));

quil.core.ellipse.call(null,(0),(0),(10),(10));

return fractal_trees.organic_tree.branch.call(null,(100),(4),(1),new cljs.core.Keyword(null,"angle","angle",1622094254).cljs$core$IFn$_invoke$arity$1(state));
});
fractal_trees.organic_tree.fractal_trees = (function fractal_trees$organic_tree$fractal_trees(){
return quil.sketch.sketch.call(null,new cljs.core.Keyword(null,"host","host",-1558485167),"fractal-trees",new cljs.core.Keyword(null,"features","features",-1146962336),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"keep-on-top","keep-on-top",-970284267)], null),new cljs.core.Keyword(null,"update","update",1045576396),((cljs.core.fn_QMARK_.call(null,fractal_trees.organic_tree.update_state))?(function() { 
var G__8486__delegate = function (args){
return cljs.core.apply.call(null,fractal_trees.organic_tree.update_state,args);
};
var G__8486 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__8487__i = 0, G__8487__a = new Array(arguments.length -  0);
while (G__8487__i < G__8487__a.length) {G__8487__a[G__8487__i] = arguments[G__8487__i + 0]; ++G__8487__i;}
  args = new cljs.core.IndexedSeq(G__8487__a,0);
} 
return G__8486__delegate.call(this,args);};
G__8486.cljs$lang$maxFixedArity = 0;
G__8486.cljs$lang$applyTo = (function (arglist__8488){
var args = cljs.core.seq(arglist__8488);
return G__8486__delegate(args);
});
G__8486.cljs$core$IFn$_invoke$arity$variadic = G__8486__delegate;
return G__8486;
})()
:fractal_trees.organic_tree.update_state),new cljs.core.Keyword(null,"size","size",1098693007),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(640),(480)], null),new cljs.core.Keyword(null,"setup","setup",1987730512),((cljs.core.fn_QMARK_.call(null,fractal_trees.organic_tree.setup))?(function() { 
var G__8489__delegate = function (args){
return cljs.core.apply.call(null,fractal_trees.organic_tree.setup,args);
};
var G__8489 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__8490__i = 0, G__8490__a = new Array(arguments.length -  0);
while (G__8490__i < G__8490__a.length) {G__8490__a[G__8490__i] = arguments[G__8490__i + 0]; ++G__8490__i;}
  args = new cljs.core.IndexedSeq(G__8490__a,0);
} 
return G__8489__delegate.call(this,args);};
G__8489.cljs$lang$maxFixedArity = 0;
G__8489.cljs$lang$applyTo = (function (arglist__8491){
var args = cljs.core.seq(arglist__8491);
return G__8489__delegate(args);
});
G__8489.cljs$core$IFn$_invoke$arity$variadic = G__8489__delegate;
return G__8489;
})()
:fractal_trees.organic_tree.setup),new cljs.core.Keyword(null,"middleware","middleware",1462115504),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [quil.middleware.fun_mode], null),new cljs.core.Keyword(null,"draw","draw",1358331674),((cljs.core.fn_QMARK_.call(null,fractal_trees.organic_tree.draw_state))?(function() { 
var G__8492__delegate = function (args){
return cljs.core.apply.call(null,fractal_trees.organic_tree.draw_state,args);
};
var G__8492 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__8493__i = 0, G__8493__a = new Array(arguments.length -  0);
while (G__8493__i < G__8493__a.length) {G__8493__a[G__8493__i] = arguments[G__8493__i + 0]; ++G__8493__i;}
  args = new cljs.core.IndexedSeq(G__8493__a,0);
} 
return G__8492__delegate.call(this,args);};
G__8492.cljs$lang$maxFixedArity = 0;
G__8492.cljs$lang$applyTo = (function (arglist__8494){
var args = cljs.core.seq(arglist__8494);
return G__8492__delegate(args);
});
G__8492.cljs$core$IFn$_invoke$arity$variadic = G__8492__delegate;
return G__8492;
})()
:fractal_trees.organic_tree.draw_state));
});
goog.exportSymbol('fractal_trees.organic_tree.fractal_trees', fractal_trees.organic_tree.fractal_trees);

if(cljs.core.truth_(cljs.core.some.call(null,(function (p1__8011__8012__auto__){
return cljs.core._EQ_.call(null,new cljs.core.Keyword(null,"no-start","no-start",1381488856),p1__8011__8012__auto__);
}),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"keep-on-top","keep-on-top",-970284267)], null)))){
} else {
quil.sketch.add_sketch_to_init_list.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"fn","fn",-1175266204),fractal_trees.organic_tree.fractal_trees,new cljs.core.Keyword(null,"host-id","host-id",742376279),"fractal-trees"], null));
}

//# sourceMappingURL=organic_tree.js.map
// Compiled by ClojureScript 1.9.473 {:static-fns true, :optimize-constants true}
goog.provide('quil.middlewares.deprecated_options');
goog.require('cljs.core');
goog.require('cljs.core.constants');
quil.middlewares.deprecated_options.deprecated = new cljs.core.PersistentArrayMap(null, 3, [cljs.core.cst$kw$decor,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, ["2.0","Try :features [:present] for similar effect"], null),cljs.core.cst$kw$target,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, ["2.0","Use :features [:keep-on-top] instead."], null),cljs.core.cst$kw$safe_DASH_draw_DASH_fn,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, ["2.0","Use :features [:no-safe-fns] instead."], null)], null);
quil.middlewares.deprecated_options.check_features_vector = (function quil$middlewares$deprecated_options$check_features_vector(features){
var features__$1 = cljs.core.set(features);
if(cljs.core.truth_((function (){var G__9749 = cljs.core.cst$kw$no_DASH_safe_DASH_draw;
return (features__$1.cljs$core$IFn$_invoke$arity$1 ? features__$1.cljs$core$IFn$_invoke$arity$1(G__9749) : features__$1.call(null,G__9749));
})())){
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["Feature :no-safe-draw was renamed to :no-safe-fns in Quil 2.1.","Use :feature [:no-safe-fns] now."], 0));
} else {
}

return cljs.core.disj.cljs$core$IFn$_invoke$arity$2(features__$1,cljs.core.cst$kw$no_DASH_safe_DASH_draw);
});
/**
 * Checks if options map contains deprected options and removes them.
 *   Prints messages how to fix deprecated functions.
 */
quil.middlewares.deprecated_options.deprecated_options = (function quil$middlewares$deprecated_options$deprecated_options(options){
var options__$1 = cljs.core.update_in.cljs$core$IFn$_invoke$arity$3(options,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$features], null),quil.middlewares.deprecated_options.check_features_vector);
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,cljs.core.remove.cljs$core$IFn$_invoke$arity$2(cljs.core.nil_QMARK_,(function (){var iter__7602__auto__ = ((function (options__$1){
return (function quil$middlewares$deprecated_options$deprecated_options_$_iter__9780(s__9781){
return (new cljs.core.LazySeq(null,((function (options__$1){
return (function (){
var s__9781__$1 = s__9781;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__9781__$1);
if(temp__4657__auto__){
var s__9781__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__9781__$2)){
var c__7600__auto__ = cljs.core.chunk_first(s__9781__$2);
var size__7601__auto__ = cljs.core.count(c__7600__auto__);
var b__9783 = cljs.core.chunk_buffer(size__7601__auto__);
if((function (){var i__9782 = (0);
while(true){
if((i__9782 < size__7601__auto__)){
var vec__9798 = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7600__auto__,i__9782);
var name = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9798,(0),null);
var value = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9798,(1),null);
cljs.core.chunk_append(b__9783,(function (){var temp__4655__auto__ = (quil.middlewares.deprecated_options.deprecated.cljs$core$IFn$_invoke$arity$1 ? quil.middlewares.deprecated_options.deprecated.cljs$core$IFn$_invoke$arity$1(name) : quil.middlewares.deprecated_options.deprecated.call(null,name));
if(cljs.core.truth_(temp__4655__auto__)){
var vec__9801 = temp__4655__auto__;
var version = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9801,(0),null);
var message = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9801,(1),null);
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([name,"option was removed in Quil",version,".",message], 0));

return null;
} else {
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [name,value], null);
}
})());

var G__9810 = (i__9782 + (1));
i__9782 = G__9810;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__9783),quil$middlewares$deprecated_options$deprecated_options_$_iter__9780(cljs.core.chunk_rest(s__9781__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__9783),null);
}
} else {
var vec__9804 = cljs.core.first(s__9781__$2);
var name = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9804,(0),null);
var value = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9804,(1),null);
return cljs.core.cons((function (){var temp__4655__auto__ = (quil.middlewares.deprecated_options.deprecated.cljs$core$IFn$_invoke$arity$1 ? quil.middlewares.deprecated_options.deprecated.cljs$core$IFn$_invoke$arity$1(name) : quil.middlewares.deprecated_options.deprecated.call(null,name));
if(cljs.core.truth_(temp__4655__auto__)){
var vec__9807 = temp__4655__auto__;
var version = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9807,(0),null);
var message = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__9807,(1),null);
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([name,"option was removed in Quil",version,".",message], 0));

return null;
} else {
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [name,value], null);
}
})(),quil$middlewares$deprecated_options$deprecated_options_$_iter__9780(cljs.core.rest(s__9781__$2)));
}
} else {
return null;
}
break;
}
});})(options__$1))
,null,null));
});})(options__$1))
;
return iter__7602__auto__(options__$1);
})()));
});

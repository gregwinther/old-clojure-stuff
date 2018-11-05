// Compiled by ClojureScript 1.7.170 {}
goog.provide('random_walker.core');
goog.require('cljs.core');
goog.require('quil.core');
goog.require('quil.middleware');
random_walker.core.width = (880);
random_walker.core.height = (640);
random_walker.core.step = (8);
random_walker.core.abs = (function random_walker$core$abs(v){
if((v < (0))){
return (- v);
} else {
return v;
}
});
random_walker.core.legal_y_QMARK_ = (function random_walker$core$legal_y_QMARK_(y){
return (random_walker.core.abs.call(null,y) <= ((random_walker.core.height / (2)) - random_walker.core.step));
});
random_walker.core.legal_y_QMARK_.call(null,(10));
random_walker.core.legal_x_QMARK_ = (function random_walker$core$legal_x_QMARK_(x){
return (random_walker.core.abs.call(null,x) <= ((random_walker.core.width / (2)) - random_walker.core.step));
});
random_walker.core.legal_pos_QMARK_ = (function random_walker$core$legal_pos_QMARK_(p__6289){
var vec__6291 = p__6289;
var x = cljs.core.nth.call(null,vec__6291,(0),null);
var y = cljs.core.nth.call(null,vec__6291,(1),null);
var and__4673__auto__ = random_walker.core.legal_x_QMARK_.call(null,x);
if(cljs.core.truth_(and__4673__auto__)){
return random_walker.core.legal_y_QMARK_.call(null,y);
} else {
return and__4673__auto__;
}
});
random_walker.core.update_pos = (function random_walker$core$update_pos(pos,vel,speed){
var new_pos = cljs.core.vec.call(null,cljs.core.map.call(null,cljs.core._PLUS_,pos,cljs.core.map.call(null,(function (p1__6292_SHARP_){
return (speed * p1__6292_SHARP_);
}),vel)));
if(cljs.core.truth_(random_walker.core.legal_pos_QMARK_.call(null,new_pos))){
return new_pos;
} else {
return pos;
}
});
random_walker.core.update_vel = (function random_walker$core$update_vel(vel){
var choices = new cljs.core.PersistentArrayMap(null, 4, [(0),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(-1),(0)], null),(1),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(1)], null),(2),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(1),(0)], null),(3),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(-1)], null)], null);
return cljs.core.get.call(null,choices,cljs.core.rand_int.call(null,(4)));
});
random_walker.core.update_vel.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(1)], null));
random_walker.core.update_state = (function random_walker$core$update_state(state){
return cljs.core.update.call(null,cljs.core.update.call(null,state,new cljs.core.Keyword(null,"pos","pos",-864607220),random_walker.core.update_pos,new cljs.core.Keyword(null,"vel","vel",-110770822).cljs$core$IFn$_invoke$arity$1(state),new cljs.core.Keyword(null,"speed","speed",1257663751).cljs$core$IFn$_invoke$arity$1(state)),new cljs.core.Keyword(null,"vel","vel",-110770822),random_walker.core.update_vel);
});
random_walker.core.get_random_color = (function random_walker$core$get_random_color(){
(cljs.core.rand_int.call(null,(2)) + (1));


return (1);
});

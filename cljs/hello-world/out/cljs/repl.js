// Compiled by ClojureScript 1.9.293 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
goog.require('cljs.spec');
cljs.repl.print_doc = (function cljs$repl$print_doc(p__1204){
var map__1229 = p__1204;
var map__1229__$1 = ((((!((map__1229 == null)))?((((map__1229.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__1229.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__1229):map__1229);
var m = map__1229__$1;
var n = cljs.core.get.call(null,map__1229__$1,new cljs.core.Keyword(null,"ns","ns",441598760));
var nm = cljs.core.get.call(null,map__1229__$1,new cljs.core.Keyword(null,"name","name",1843675177));
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str((function (){var temp__4657__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4657__auto__)){
var ns = temp__4657__auto__;
return [cljs.core.str(ns),cljs.core.str("/")].join('');
} else {
return null;
}
})()),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__1231_1253 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__1232_1254 = null;
var count__1233_1255 = (0);
var i__1234_1256 = (0);
while(true){
if((i__1234_1256 < count__1233_1255)){
var f_1257 = cljs.core._nth.call(null,chunk__1232_1254,i__1234_1256);
cljs.core.println.call(null,"  ",f_1257);

var G__1258 = seq__1231_1253;
var G__1259 = chunk__1232_1254;
var G__1260 = count__1233_1255;
var G__1261 = (i__1234_1256 + (1));
seq__1231_1253 = G__1258;
chunk__1232_1254 = G__1259;
count__1233_1255 = G__1260;
i__1234_1256 = G__1261;
continue;
} else {
var temp__4657__auto___1262 = cljs.core.seq.call(null,seq__1231_1253);
if(temp__4657__auto___1262){
var seq__1231_1263__$1 = temp__4657__auto___1262;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__1231_1263__$1)){
var c__3675__auto___1264 = cljs.core.chunk_first.call(null,seq__1231_1263__$1);
var G__1265 = cljs.core.chunk_rest.call(null,seq__1231_1263__$1);
var G__1266 = c__3675__auto___1264;
var G__1267 = cljs.core.count.call(null,c__3675__auto___1264);
var G__1268 = (0);
seq__1231_1253 = G__1265;
chunk__1232_1254 = G__1266;
count__1233_1255 = G__1267;
i__1234_1256 = G__1268;
continue;
} else {
var f_1269 = cljs.core.first.call(null,seq__1231_1263__$1);
cljs.core.println.call(null,"  ",f_1269);

var G__1270 = cljs.core.next.call(null,seq__1231_1263__$1);
var G__1271 = null;
var G__1272 = (0);
var G__1273 = (0);
seq__1231_1253 = G__1270;
chunk__1232_1254 = G__1271;
count__1233_1255 = G__1272;
i__1234_1256 = G__1273;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_1274 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__3289__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__3289__auto__)){
return or__3289__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_1274);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_1274)))?cljs.core.second.call(null,arglists_1274):arglists_1274));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/"),cljs.core.str(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/special_forms#"),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__1235_1275 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__1236_1276 = null;
var count__1237_1277 = (0);
var i__1238_1278 = (0);
while(true){
if((i__1238_1278 < count__1237_1277)){
var vec__1239_1279 = cljs.core._nth.call(null,chunk__1236_1276,i__1238_1278);
var name_1280 = cljs.core.nth.call(null,vec__1239_1279,(0),null);
var map__1242_1281 = cljs.core.nth.call(null,vec__1239_1279,(1),null);
var map__1242_1282__$1 = ((((!((map__1242_1281 == null)))?((((map__1242_1281.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__1242_1281.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__1242_1281):map__1242_1281);
var doc_1283 = cljs.core.get.call(null,map__1242_1282__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_1284 = cljs.core.get.call(null,map__1242_1282__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_1280);

cljs.core.println.call(null," ",arglists_1284);

if(cljs.core.truth_(doc_1283)){
cljs.core.println.call(null," ",doc_1283);
} else {
}

var G__1285 = seq__1235_1275;
var G__1286 = chunk__1236_1276;
var G__1287 = count__1237_1277;
var G__1288 = (i__1238_1278 + (1));
seq__1235_1275 = G__1285;
chunk__1236_1276 = G__1286;
count__1237_1277 = G__1287;
i__1238_1278 = G__1288;
continue;
} else {
var temp__4657__auto___1289 = cljs.core.seq.call(null,seq__1235_1275);
if(temp__4657__auto___1289){
var seq__1235_1290__$1 = temp__4657__auto___1289;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__1235_1290__$1)){
var c__3675__auto___1291 = cljs.core.chunk_first.call(null,seq__1235_1290__$1);
var G__1292 = cljs.core.chunk_rest.call(null,seq__1235_1290__$1);
var G__1293 = c__3675__auto___1291;
var G__1294 = cljs.core.count.call(null,c__3675__auto___1291);
var G__1295 = (0);
seq__1235_1275 = G__1292;
chunk__1236_1276 = G__1293;
count__1237_1277 = G__1294;
i__1238_1278 = G__1295;
continue;
} else {
var vec__1244_1296 = cljs.core.first.call(null,seq__1235_1290__$1);
var name_1297 = cljs.core.nth.call(null,vec__1244_1296,(0),null);
var map__1247_1298 = cljs.core.nth.call(null,vec__1244_1296,(1),null);
var map__1247_1299__$1 = ((((!((map__1247_1298 == null)))?((((map__1247_1298.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__1247_1298.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__1247_1298):map__1247_1298);
var doc_1300 = cljs.core.get.call(null,map__1247_1299__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_1301 = cljs.core.get.call(null,map__1247_1299__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_1297);

cljs.core.println.call(null," ",arglists_1301);

if(cljs.core.truth_(doc_1300)){
cljs.core.println.call(null," ",doc_1300);
} else {
}

var G__1302 = cljs.core.next.call(null,seq__1235_1290__$1);
var G__1303 = null;
var G__1304 = (0);
var G__1305 = (0);
seq__1235_1275 = G__1302;
chunk__1236_1276 = G__1303;
count__1237_1277 = G__1304;
i__1238_1278 = G__1305;
continue;
}
} else {
}
}
break;
}
} else {
}

if(cljs.core.truth_(n)){
var temp__4657__auto__ = cljs.spec.get_spec.call(null,cljs.core.symbol.call(null,[cljs.core.str(cljs.core.ns_name.call(null,n))].join(''),cljs.core.name.call(null,nm)));
if(cljs.core.truth_(temp__4657__auto__)){
var fnspec = temp__4657__auto__;
cljs.core.print.call(null,"Spec");

var seq__1249 = cljs.core.seq.call(null,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword(null,"fn","fn",-1175266204)], null));
var chunk__1250 = null;
var count__1251 = (0);
var i__1252 = (0);
while(true){
if((i__1252 < count__1251)){
var role = cljs.core._nth.call(null,chunk__1250,i__1252);
var temp__4657__auto___1306__$1 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___1306__$1)){
var spec_1307 = temp__4657__auto___1306__$1;
cljs.core.print.call(null,[cljs.core.str("\n "),cljs.core.str(cljs.core.name.call(null,role)),cljs.core.str(":")].join(''),cljs.spec.describe.call(null,spec_1307));
} else {
}

var G__1308 = seq__1249;
var G__1309 = chunk__1250;
var G__1310 = count__1251;
var G__1311 = (i__1252 + (1));
seq__1249 = G__1308;
chunk__1250 = G__1309;
count__1251 = G__1310;
i__1252 = G__1311;
continue;
} else {
var temp__4657__auto____$1 = cljs.core.seq.call(null,seq__1249);
if(temp__4657__auto____$1){
var seq__1249__$1 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__1249__$1)){
var c__3675__auto__ = cljs.core.chunk_first.call(null,seq__1249__$1);
var G__1312 = cljs.core.chunk_rest.call(null,seq__1249__$1);
var G__1313 = c__3675__auto__;
var G__1314 = cljs.core.count.call(null,c__3675__auto__);
var G__1315 = (0);
seq__1249 = G__1312;
chunk__1250 = G__1313;
count__1251 = G__1314;
i__1252 = G__1315;
continue;
} else {
var role = cljs.core.first.call(null,seq__1249__$1);
var temp__4657__auto___1316__$2 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___1316__$2)){
var spec_1317 = temp__4657__auto___1316__$2;
cljs.core.print.call(null,[cljs.core.str("\n "),cljs.core.str(cljs.core.name.call(null,role)),cljs.core.str(":")].join(''),cljs.spec.describe.call(null,spec_1317));
} else {
}

var G__1318 = cljs.core.next.call(null,seq__1249__$1);
var G__1319 = null;
var G__1320 = (0);
var G__1321 = (0);
seq__1249 = G__1318;
chunk__1250 = G__1319;
count__1251 = G__1320;
i__1252 = G__1321;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map
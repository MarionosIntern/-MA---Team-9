const PRODUCTS = [
    {id: 'jacket', name: 'Denim Tear Jacket', price: 50.00, description: "Product By: Seller A"},
    {id: 'necklace', name: 'Gold Necklace', price: 80.00, description: "Product By: Seller B"},
    {id: 'shampoo', name: 'Herbal Shampoo', price: 15.00, description: "Product By: Seller C"},
    {id: 'keyboard', name: 'Mechanical Keyboard', price: 120.00, description: "Product By: Seller D"}
];

const USER_KEY='cm_user';
function getUser(){ try{ return JSON.parse(localStorage.getItem(USER_KEY)||'null'); }catch{ return null } }
function emailKey(){ const u=getUser(); return u && u.email ? encodeURIComponent(String(u.email).toLowerCase()) : 'guest'; }
function CART_KEY(){ return `cm_cart_${emailKey()}`; }
function FAV_KEY(){ return `cm_faves_${emailKey()}`; }

function getFaves(){ try{ return new Set(JSON.parse(localStorage.getItem(FAV_KEY())||'[]')); }catch{ return new Set() } }
function setFaves(set){ localStorage.setItem(FAV_KEY(), JSON.stringify(Array.from(set))); }

function getCart(){ try{ return JSON.parse(localStorage.getItem(CART_KEY())||'[]'); }catch{ return [] } }
function setCart(arr){ localStorage.setItem(CART_KEY(), JSON.stringify(arr)); }
function addToCart(name, price){ const cart=getCart(); cart.push({name, price}); setCart(cart); alert(`${name} added to cart`); }

const grid = document.getElementById('grid');
const search = document.getElementById('search');
const onlyFavs = document.getElementById('favs');

function render(){
const q = (search.value||'').toLowerCase();
const favs = getFaves();
grid.innerHTML='';
PRODUCTS.filter(p=> p.name.toLowerCase().includes(q) && (!onlyFavs.checked || favs.has(p.id))).forEach(p=>{
const card=document.createElement('div'); card.className='card';
const isFav=favs.has(p.id);
card.innerHTML=`<div class="row"><h3>${p.name}</h3><button class="fav ${isFav?'active':''}" aria-label="favorite" data-id="${p.id}">${isFav?'♥':'♡'}</button></div>
<div class="row"><span class="price">$${p.price.toFixed(2)}</span><button class="btn" data-add="${p.id}">Add to Cart</button></div>
<div class="muted">ID: ${p.id}</div>`;
grid.appendChild(card);
});
}

grid.addEventListener('click', (e)=>{
const favBtn = e.target.closest('button.fav');
if(favBtn){
const id=favBtn.getAttribute('data-id');
const favs=getFaves();
if(favs.has(id)) favs.delete(id); else favs.add(id);
setFaves(favs); render(); return;
}
const addBtn = e.target.closest('button.btn[data-add]');
if(addBtn){
const id = addBtn.getAttribute('data-add');
const p = PRODUCTS.find(x=>x.id===id);
addToCart(p.name, p.price);
}
});

search.addEventListener('input', render);
favs.addEventListener('change', render);
render();


// ------- Profile panel -------
const openBtn=document.getElementById('openProfile');
const closeBtn=document.getElementById('closeProfile');
const panel=document.getElementById('profilePanel');
openBtn.addEventListener('click',()=>togglePanel(true));
closeBtn.addEventListener('click',()=>togglePanel(false));
function togglePanel(open){ panel.classList.toggle('open',open); panel.setAttribute('aria-hidden', String(!open)); openBtn.setAttribute('aria-expanded', String(open)); if(open) renderProfile(); }

function renderProfile(){
const root=document.getElementById('profileContent');
const u=getUser();
if(u){ root.innerHTML=`<div class="profile-card"><div style="display:flex;align-items:center"><div class="avatar">${(u.name||'?').charAt(0).toUpperCase()}</div><div><h3 style="margin:0">${u.name}</h3><div class="muted">${u.email||''}</div></div></div><p class="helper">Signed in on this browser only.</p><button class="btn secondary" onclick="logout()">Log out</button></div>`; }
else { root.innerHTML=`<div class="auth-tabs"><button id="tabSignup" class="active" onclick="switchAuth('signup')">Sign Up</button><button id="tabLogin" onclick="switchAuth('login')">Log In</button></div>
<form id="formSignup" class="auth-form active" onsubmit="event.preventDefault(); handleSignUp();">
<label for="su_name">Full name</label><input id="su_name" required>
<label for="su_email">Email</label><input id="su_email" type="email" required>
<label for="su_pass">Password</label><input id="su_pass" type="password" minlength="4" required>
<button class="btn" style="margin-top:12px">Create account</button>
</form>
<form id="formLogin" class="auth-form" onsubmit="event.preventDefault(); handleLogin();">
<label for="li_email">Email</label><input id="li_email" type="email" required>
<label for="li_pass">Password</label><input id="li_pass" type="password" minlength="4" required>
<button class="btn" style="margin-top:12px">Log in</button>
</form>`; }
}
function switchAuth(which){ document.getElementById('tabSignup').classList.toggle('active', which==='signup'); document.getElementById('tabLogin').classList.toggle('active', which==='login'); document.getElementById('formSignup').classList.toggle('active', which==='signup'); document.getElementById('formLogin').classList.toggle('active', which==='login'); }
function setUser(u){ localStorage.setItem(USER_KEY, JSON.stringify(u)); }
function handleSignUp(){ const name=su_name.value.trim(); const email=su_email.value.trim().toLowerCase(); const pass=su_pass.value; if(!name||!email||!pass){alert('Fill all fields');return;} setUser({name,email,pass}); renderProfile(); }
function handleLogin(){ const email=li_email.value.trim().toLowerCase(); const pass=li_pass.value; const u=getUser(); if(!u){alert('No account here yet; sign up.');return;} if(u.email===email && u.pass===pass){ renderProfile(); } else { alert('Invalid email or password'); } }
function logout(){ localStorage.removeItem(USER_KEY); renderProfile(); }
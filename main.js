let listaItens =[];
let itemaEditar;
const form = document.getElementById('form-itens');
const itensInput = document.getElementById('receber-item');
const caixaItem = document.getElementById('lista-de-itens');
const caixaItensComprados = document.getElementById('itens-comprados');
const listaRecuperada = localStorage.getItem('listaItens');

function atualizaLocalStorage(){
    localStorage.setItem('listaItens',JSON.stringify(listaItens));
}
if(listaRecuperada){
    listaItens = JSON.parse(listaRecuperada);
    mostrarItem();
}else{
    listaItens = [];
}
form.addEventListener("submit",function(evento){
    evento.preventDefault();
    adicionarItem();
    limparCampo();
    mostrarItem();
})
function adicionarItem(){
const aux = itensInput.value;
const check = listaItens.some((itentest)=> itentest.item.toUpperCase() === aux.toUpperCase())
    if(check){
    alert('Este item ja esta presente na lista de compras');
    }else{
    listaItens.push({
    item: aux,
    checar: false
        });
    }
}
function limparCampo(){
    itensInput.value='';
}
function mostrarItem(){
    caixaItem.innerHTML = '';
    caixaItensComprados.innerHTML = '';
    listaItens.forEach((elemento,index)=> {
        if(elemento.checar){
            caixaItensComprados.innerHTML +=`<li class="item-compra is-flex is-justify-content-space-between" data-value="${index}">
        <div>
            <input type="checkbox" checked class="is-clickable" />  
            <span class="itens-comprados is-size-5">${elemento.item}</span>
        </div>
        <div>
            <i class="fa-solid fa-trash is-clickable deletar"></i>
        </div>
    </li>`}else{
    caixaItem.innerHTML +=`<li class="item-compra is-flex is-justify-content-space-between" data-value="${index}">
        <div>
            <input type="checkbox" class="is-clickable" />
            <input type="text" class="is-size-5" value="${elemento.item}" ${index != itemaEditar ? 'disabled':'' }></input>
        </div>
        <div>
            ${index == itemaEditar ?'<button onclick="salvarEdicao()"><i class="fa-regular fa-floppy-disk is-clickable"></i></button>' : '<i class="fa-regular is-clickable fa-pen-to-square editar"></i>'}
            <i class="fa-solid fa-trash is-clickable deletar"></i>
        </div>
    </li>`
        } 
    })
const inputCheck = document.querySelectorAll('input[type="checkbox"]');
inputCheck.forEach(i => {
    i.addEventListener('click',(evento) =>{
      const valorDoElemento =  evento.target.parentElement.parentElement.getAttribute('data-value');
      listaItens[valorDoElemento].checar = evento.target.checked
      mostrarItem();
    })
})

const deletarObjetos = document.querySelectorAll(".deletar")
deletarObjetos.forEach(i => {
    i.addEventListener('click',(evento) =>{
      valorDoElemento =  evento.target.parentElement.parentElement.getAttribute('data-value');
     listaItens.splice(valorDoElemento,1)
      mostrarItem();
    })
})

const editarItens = document.querySelectorAll('.editar');
editarItens.forEach(i => {
    i.addEventListener('click',(evento) =>{
      itemaEditar =  evento.target.parentElement.parentElement.getAttribute('data-value');
      document.querySelector(".is-size-5").removeAttribute('disabled')
      mostrarItem();
    })
})
atualizaLocalStorage();
}
function salvarEdicao(){
    const itemEditado = document.querySelector(`[data-value="${itemaEditar}"] input[type="text"]`)
    listaItens[itemaEditar].item = itemEditado.value;
    console.log(listaItens);
    itemaEditar = -1;
    mostrarItem();
}

package com.trabalhoweb.trabalhoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.trabalhoweb.trabalhoweb.models.Produto;
import com.trabalhoweb.trabalhoweb.repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository pr;
	
	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.GET)
	public String form() {
		return "produto/formProduto";
	}

	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.POST)
	public String form(Produto produto) {
		
		pr.save(produto);
		
		return "redirect:/cadastrarProduto";
	}

	@RequestMapping("/produtos")
	public ModelAndView listaProd() {
		ModelAndView mv = new ModelAndView("produto/listarProdutos");
		
		Iterable<Produto> produtos = pr.findAll();
		
		mv.addObject("produtos", produtos);
		
		return mv;
	}
	
	@RequestMapping("/listarProdutos")
	public ModelAndView listarProdutosParaClientes() {
		ModelAndView mv = new ModelAndView("index");
		
		Iterable<Produto> produtos = pr.findAll();
		
		mv.addObject("produtos", produtos);
		
		return mv;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView detalhesProduto(@PathVariable("id") Long id) {
		Produto produto = pr.findById(id);
		
		ModelAndView mv = new ModelAndView("produto/detalhesProduto");
		
		mv.addObject("produto", produto);
		
		return mv;
	}
	
	@RequestMapping("/deletarProduto")
	public String deletarProduto(long id) {
		
		Produto produto = pr.findById(id);
		pr.delete(produto);
		
		return "redirect:/produtos";
	}
}


package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import com.example.jeedemo.domain.Gasnica;
import com.example.jeedemo.domain.Producent;
import com.example.jeedemo.domain.Wlasciciel;
import com.example.jeedemo.service.GasnicaManager;
import com.example.jeedemo.service.ProducentManager;
import com.example.jeedemo.service.WlascicielManager;

@SessionScoped
@Named("gasnicaBean")
public class GasnicaFormBean implements Serializable {
	
	private Gasnica gasnica = new Gasnica();
	private ListDataModel<Gasnica> gasnice = new ListDataModel<Gasnica>();
	private DualListModel<Wlasciciel> tempList; 
	private List<Wlasciciel> wlascicieleTemp ;
	
	private Long gasnicaId;
	private Long producentId;
	private Long wlascicielId;
	
	@Inject
	GasnicaManager gm;
	@Inject
	ProducentManager pm;
	@Inject
	WlascicielManager wm;	

	
	public Gasnica getGasnica() {
		return gasnica;
	}
	
	public void setGasnica(Gasnica gasnica) {
		this.gasnica = gasnica;
	}

	// Actions
	public String addGasnica() {
		
		gm.addGasnica(gasnica);
		gm.addProducentToGasnica(gasnica.getId(), producentId);		
		gm.addWlascicielToGasnicaList(gasnica.getId(), wlascicieleTemp);

		return null;
	}
	
	public String deleteGasnica(Gasnica gasnica){
		gm.deleteGasnica(gasnica);
		return null;
	}

	public ListDataModel<Gasnica> getAllGasnice() {
		gasnice.setWrappedData(gm.getAllGasnice());
		return gasnice;
	}

	public Long getGasnicaId() {
		return gasnicaId;
	}

	public void setGasnicaId(Long gasnicaId) {
		this.gasnicaId = gasnicaId;
	}

	public Long getProducentId() {
		return producentId;
	}

	public void setProducentId(Long producentId) {
		this.producentId = producentId;
	}
	
	public List<Producent> getAllProducenci(){
		return pm.getAllProducenci();
	}

	public List<Wlasciciel> getAllWlasciciele(){
		return wm.getAllWlasciciele();
	}
	
	public Long getWlascicielId() {
		return wlascicielId;
	}

	public void setWlascicielId(Long wlascicielId) {
		this.wlascicielId = wlascicielId;
	}

	public DualListModel<Wlasciciel> getTempList() {
		return (new DualListModel<Wlasciciel>(wm.getAllWlasciciele(), new ArrayList<Wlasciciel>()));
	}

	public void setTempList(DualListModel<Wlasciciel> tempList) {
		this.tempList = tempList;
	}

    public List<Wlasciciel> getSelected() {
        return tempList.getTarget();
    }

	public List<Wlasciciel> getWlascicieleTemp() {
		return wlascicieleTemp;
	}

	public void setWlascicieleTemp(List<Wlasciciel> wlascicieleTemp) {
		this.wlascicieleTemp = wlascicieleTemp;
	}

	
	public String doEdycji(Gasnica gasnica){
		this.gasnica = gasnica;
		return "edytujGasnice";
	}
	
	public String edytujGasnica(){
		gm.edytujGasnica(gasnica);
		this.gasnica = new Gasnica();
		return null;
	}
	
}

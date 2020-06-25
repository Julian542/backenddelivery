package com.utn.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.PlatosPorClienteDTO;
import com.utn.demo.entity.DetallePedido;
import com.utn.demo.entity.Pedido;
import com.utn.demo.repository.PlatosPorClienteRepository;

@Service
public class PlatosPorClienteService {

	private PlatosPorClienteRepository platosPorClienteRepository;

	public PlatosPorClienteService(PlatosPorClienteRepository platosPorClienteRepository) {
		this.platosPorClienteRepository = platosPorClienteRepository;
	}

	// buscarPedidos
	public List<PlatosPorClienteDTO> getOne(long idUsuario, int mes) throws Exception {
		List<PlatosPorClienteDTO> platosPorClienteDTOs = new ArrayList<>();
		List<String> nombrePlatos = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		now.set(Calendar.MONTH, mes);
		now.set(Calendar.DAY_OF_MONTH, 1);
		Date primerDia = now.getTime();
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date ultimoDia = now.getTime();
		try {
			List<Pedido> pedidos = platosPorClienteRepository.buscarPedidos(primerDia, ultimoDia, idUsuario);
			for (Pedido pedido : pedidos) {
				for (DetallePedido detallePedido : pedido.getDetallePedido()) {
					if (nombrePlatos.contains(detallePedido.getPlato().getNombre())) {
						int index = nombrePlatos.indexOf(detallePedido.getPlato().getNombre());
						int cant = platosPorClienteDTOs.get(index).getCantidad();
						++cant;
						PlatosPorClienteDTO ppc = platosPorClienteDTOs.get(index);
						ppc.setCantidad(cant);
						platosPorClienteDTOs.set(index, ppc);
					} else {
						PlatosPorClienteDTO platosPorClienteDTO = new PlatosPorClienteDTO();
						platosPorClienteDTO.setCantidad(1);
						platosPorClienteDTO.setApellido(pedido.getPedidoCliente().getApellido());
						platosPorClienteDTO.setEmail(pedido.getPedidoCliente().getEmail());
						platosPorClienteDTO.setNombre(pedido.getPedidoCliente().getNombre());
						platosPorClienteDTO.setId(pedido.getPedidoCliente().getId());
						platosPorClienteDTO.setNombrePlato(detallePedido.getPlato().getNombre());
						nombrePlatos.add(detallePedido.getPlato().getNombre());

						switch (mes) {
						case 0:
							platosPorClienteDTO.setPeriodo("Enero");
							break;
						case 1:
							platosPorClienteDTO.setPeriodo("Febrero");
							break;
						case 2:
							platosPorClienteDTO.setPeriodo("Marzo");
							break;
						case 3:
							platosPorClienteDTO.setPeriodo("Abril");
							break;
						case 4:
							platosPorClienteDTO.setPeriodo("Mayo");
							break;
						case 5:
							platosPorClienteDTO.setPeriodo("Junio");
							break;
						case 6:
							platosPorClienteDTO.setPeriodo("Julio");
							break;
						case 7:
							platosPorClienteDTO.setPeriodo("Agosto");
							break;
						case 8:
							platosPorClienteDTO.setPeriodo("Septiembre");
							break;
						case 9:
							platosPorClienteDTO.setPeriodo("Octubre");
							break;
						case 10:
							platosPorClienteDTO.setPeriodo("Noviembre");
							break;
						case 11:
							platosPorClienteDTO.setPeriodo("Diciembre");
							break;
						default:
							break;
						}
						platosPorClienteDTOs.add(platosPorClienteDTO);
					}
				}
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return platosPorClienteDTOs;
	}
}

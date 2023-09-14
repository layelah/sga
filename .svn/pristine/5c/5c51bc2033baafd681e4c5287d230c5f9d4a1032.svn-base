/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author babacar
 */
public abstract class IpAdress {

	
/**
 * Retourne l'IP adress de l'utilisateur connecté.
 * @return
 */
	public static final String getUserIpAdress() {

		String	ipAdress = "";
		try {
			ipAdress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return ipAdress;
	}
}

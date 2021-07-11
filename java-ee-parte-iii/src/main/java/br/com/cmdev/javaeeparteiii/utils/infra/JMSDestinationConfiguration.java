package br.com.cmdev.javaeeparteiii.utils.infra;

import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(name = "java:/jms/topics/CarrinhoComprasTopico", interfaceName = "javax.jms.Topic")
public class JMSDestinationConfiguration {
}

package com.fatec.tg.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
              
                 .antMatchers("/**").permitAll(); 



                // .antMatchers("/api/representante/operacao").permitAll() 
                // .antMatchers("/whatsapp/send").permitAll() 
                // .antMatchers("/api/representante/login").permitAll() 
                // .antMatchers("/api/representante/operacao/email/**").permitAll() 
                // .antMatchers("/oauth/token").permitAll()
                // .antMatchers("/api/chamadatoken").permitAll()

                // .antMatchers("/api/representante/operacao/**").authenticated()
                // .antMatchers("/api/idoso/operacao/**").authenticated()
                // .antMatchers("/api/idoso/operacao").authenticated()
                // .antMatchers("/QRCode/idoso/**").authenticated()
                // .antMatchers("/api/qrcode/**").authenticated()
                // .antMatchers("/api/arquivos/**").authenticated()
                // .antMatchers("/api/representante/arquivos/**").authenticated();
                
                
    }
}





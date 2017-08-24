package cn.xinbee.rcs.core.io.export.config;

import java.io.IOException;
import java.util.Properties;

import cn.xinbee.rcs.core.io.BeanFieldResolver;
import cn.xinbee.rcs.core.io.FieldDescriptorsResolver;
import cn.xinbee.rcs.core.io.ReflectionBeanFieldResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import cn.xinbee.rcs.core.io.ReflectionFieldDescriptorsResolver;
import cn.xinbee.rcs.core.io.export.FileExportManager;
import cn.xinbee.rcs.core.io.export.FileExporter;
import cn.xinbee.rcs.core.io.export.excel.Excel2003Exporter;
import cn.xinbee.rcs.core.io.export.excel.ExcelFileExportManager;

@Configuration
public class FileExportConfig {
    
    @Bean
    public BeanFieldResolver beanFieldResolver(){
        return new ReflectionBeanFieldResolver();
    }
    
    @Bean
    public FileExporter excel2003Exporter(){
        return new Excel2003Exporter(beanFieldResolver());
    }
    
    @Bean
    public FieldDescriptorsResolver fieldDescriptorsResolver() throws IOException{
        Properties configProps = PropertiesLoaderUtils.loadProperties(new ClassPathResource("export/excel_fields.properties"));
        return new ReflectionFieldDescriptorsResolver(configProps);
    }
    
    @Bean
    public FileExportManager excelFileExportManager(FieldDescriptorsResolver fieldDescriptorsResolver){
        return new ExcelFileExportManager(fieldDescriptorsResolver, excel2003Exporter());
    }
}

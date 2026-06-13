package br.unitins.topicos1.brincos.model.jpa;

import br.unitins.topicos1.brincos.model.ClassificacaoTamanho;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClassificacaoTamanhoConverter implements AttributeConverter<ClassificacaoTamanho, Long>{

    @Override
    public Long convertToDatabaseColumn(ClassificacaoTamanho classificacaoTamanho) {
        return (classificacaoTamanho == null) ? null : classificacaoTamanho.ID;
    }

    @Override
    public ClassificacaoTamanho convertToEntityAttribute(Long id) {
        return ClassificacaoTamanho.valueOf(id);
    }

}
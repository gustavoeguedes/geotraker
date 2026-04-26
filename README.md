# GeoTracker - Sistema de Gestão de Veículos

Projeto de estudo de geolocalização utilizando Spring Boot e GeoTools.

---

## Requisitos Funcionais

### Cadastro de Veículos
- [x] RF001 - Cadastrar novo veículo (placa, modelo, marca, ano, cor)
- [x] RF002 - Editar dados do veículo
- [x] RF003 - Excluir veículo
- [x] RF004 - Listar todos os veículos cadastrados
- [x] RF005 - Buscar veículo por placa
- [x] RF006 - Buscar veículo por ID

### Gestão de Posição (Geolocalização)
- [ ] RF007 - Registrar posição atual do veículo (latitude, longitude)
- [ ] RF008 - Atualizar posição do veículo em tempo real
- [ ] RF009 - Consultar histórico de posições do veículo
- [ ] RF010 - Visualizar trajetória do veículo em período
- [ ] RF011 - Calcular distância percorrida pelo veículo
- [ ] RF012 - Calcular tempo de deslocamento entre pontos

### Monitoramento
- [ ] RF013 - Definir cercas geográficas (geofences)
- [ ] RF014 - Notificar quando veículo entra em geofence
- [ ] RF015 - Notificar quando veículo sai de geofence
- [ ] RF016 - Visualizar veículo no mapa em tempo real

### Relatórios
- [ ] RF017 - Relatório de quilometragem por veículo
- [ ] RF018 - Relatório de tempo em movimento
- [ ] RF019 - Relatório de veículos por localização

---

## Requisitos Não Funcionais

### Performance
- [ ] RNF001 - Atualização de posição em tempo real (< 1 segundo)
- [ ] RNF002 - Consulta de histórico com paginação
- [ ] RNF003 - Tempo de resposta da API < 200ms

### Segurança
- [ ] RNF004 - Autenticação JWT para acesso à API
- [ ] RNF005 - Autorização baseada em roles
- [ ] RNF006 - Criptografia de dados sensíveis

### Infraestrutura
- [ ] RNF007 - Banco de dados PostgreSQL com PostGIS
- [ ] RNF008 - API RESTful
- [ ] RNF009 - Container Docker
- [ ] RNF010 - Logging de operações

### Usabilidade
- [ ] RNF011 - Documentação da API (OpenAPI/Swagger)
- [ ] RNF012 - Mensagens de erro claras
- [ ] RNF013 - Validação de entrada

---

## Regras de Negócio

### Cadastro de Veículos
- [x] RN001 - Placa deve ser única no sistema
- [x] RN002 - Placa deve seguir formato brasileiro (XXX-0000 ou XXX0A00)
- [x] RN003 - Ano do veículo não pode ser futuro
- [x] RN004 - Ano do veículo não pode ser anterior a 1900

### Geolocalização
- [ ] RN005 - Coordenadas devem ser válidas (latitude -90 a 90, longitude -180 a 180)
- [ ] RN006 - Histórico de posições deve persistir por no mínimo 30 dias
- [ ] RN007 - Geofence deve ter no mínimo 3 pontos
- [ ] RN008 - Geofence deve ser um polígono válido (fechado)

### Monitoramento
- [ ] RN009 - Raio mínimo de geofence: 50 metros
- [ ] RN010 - Raio máximo de geofence: 10 quilômetros
- [ ] RN011 - Notificação deve ser assíncrona

---

## Tecnologias

- **Backend:** Spring Boot 4.0.6
- **Java:** JDK 25
- **Banco:** PostgreSQL com PostGIS
- **Build:** Maven

---

## Como Executar

```bash
# Compilar
./mvnw clean package

# Executar
./mvnw spring-boot:run

# Docker
docker-compose up -d
```

---

## API Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/veiculos | Listar veículos |
| POST | /api/veiculos | Cadastrar veículo |
| GET | /api/veiculos/{id} | Buscar veículo |
| PUT | /api/veiculos/{id} | Atualizar veículo |
| DELETE | /api/veiculos/{id} | Excluir veículo |
| POST | /api/veiculos/{id}/posicao | Registrar posição |
| GET | /api/veiculos/{id}/posicoes | Histórico de posições |
| POST | /api/geofences | Criar geofence |
| GET | /api/geofences | Listar geofences |

---

## Status de Implementação

**Total RF:** 19 requisitos
**Total RNF:** 13 requisitos  
**Total RN:** 11 regras
# My-Agent 智能对话助手

<div align="center">
    <img src="https://img.shields.io/badge/Java-21-orange.svg" alt="Java 21"/>
    <img src="https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen.svg" alt="Spring Boot 3.4.4"/>
    <img src="https://img.shields.io/badge/Spring%20AI-1.0.0--M6-blue.svg" alt="Spring AI"/>
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License"/>
</div>

## 📝 项目简介

My-Agent 是一个基于 Spring Boot 和 Spring AI 构建的智能对话助手系统，集成了 DeepSeek 大语言模型，提供强大的自然语言处理能力。系统支持会话记忆持久化、结构化输出等高级功能，可作为各类智能应用的核心对话引擎。

## ✨ 核心特性

- 🤖 **AI 对话能力**：集成 DeepSeek 大语言模型，提供流畅的对话体验
- 💾 **会话记忆持久化**：使用 Kryo 序列化技术保存对话历史
- 🧩 **结构化数据输出**：支持 JSON Schema 格式化响应
- 🔌 **易于集成**：标准 RESTful API 设计，支持多平台接入
- 🛠️ **灵活配置**：多环境配置支持，易于定制和扩展

## 🛠️ 技术栈

- **后端框架**：Spring Boot 3.4.4
- **AI 集成**：Spring AI OpenAI 适配器
- **序列化工具**：Kryo 5.6.2
- **结构化输出**：JSON Schema Generator 4.38.0
- **构建工具**：Maven

## 📦 快速开始

### 环境要求

- JDK 21+
- Maven 3.9+
- DeepSeek API 密钥

### 配置说明

1. 克隆项目代码
   ```bash
   git clone https://github.com/yourusername/my-agent.git
   cd my-agent
   ```

2. 创建 `application-local.yml` 文件并配置 API 密钥
   ```yaml
   spring:
     ai:
       openai:
         api-key: 你的API密钥
         base-url: https://api.deepseek.com
         chat:
           options:
             model: deepseek-chat
   ```

3. 编译项目
   ```bash
   ./mvnw clean package
   ```

4. 运行应用
   ```bash
   ./mvnw spring-boot:run
   ```

## 🚀 使用指南

### API 接口

服务启动后，可通过 RESTful API 与 AI 助手进行交互：

- **聊天接口**：`POST /api/chat`
- **历史会话**：`GET /api/chat/history`
- **清除会话**：`DELETE /api/chat/history`

### 前端开发

项目已配置 CORS 支持本地前端开发：

- 默认支持地址：`http://localhost:5173`
- 支持的方法：GET, POST, PUT, DELETE, OPTIONS
- 支持凭证：已启用

## 🔧 项目配置

主要配置位于 `application.yml` 和 `application-local.yml`：

- AI 模型相关配置
- 会话记忆持久化目录设置
- 环境配置

## 📚 开发指南

### 项目结构

```
my-agent/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── cn/yam/myagent/
│   │   │       ├── config/       # 配置类
│   │   │       ├── controller/   # API 控制器
│   │   │       ├── service/      # 业务逻辑
│   │   │       └── model/        # 数据模型
│   │   └── resources/            # 配置文件
│   └── test/                     # 测试代码
└── pom.xml                       # Maven 依赖
```

## 📄 许可证

本项目采用 [MIT 许可证](LICENSE) 进行授权。

## 👥 贡献指南

欢迎提交 Issue 或 Pull Request 来改进项目！

---

<div align="center">
    <p>用 ❤️ 和 ☕ 制作</p>
</div>
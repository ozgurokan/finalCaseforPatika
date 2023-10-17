import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import {BrowserRouter} from "react-router-dom"
import {store,persistor} from "./redux/store"
import {Provider} from "react-redux";
import { ChakraProvider } from '@chakra-ui/react'
import {PersistGate} from "redux-persist/lib/integration/react"

ReactDOM.createRoot(document.getElementById('root')).render(

    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
    <ChakraProvider>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </ChakraProvider>
      </PersistGate>
    </Provider>
  ,
)

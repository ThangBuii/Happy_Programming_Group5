import Header from "../component/Header";
import "../styles/Header.css";

export default function DefaultTemplets({ classNamme = "container-fluid", title, children }) {
    return (
        <div className={classNamme}>
            <Header/> 
            <div className="row">
                <h2>{title}</h2>
                
             {children}
            </div>
            
        </div>

    );
    
}
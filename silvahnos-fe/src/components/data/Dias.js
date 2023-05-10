const Dias = (year, month) => {
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    const days = [];
    for (let i = 1; i <= daysInMonth; i++) {
      const date = new Date(year, month, i);
      days.push(date);
    }
    const daysString = days.map(day => day.toLocaleDateString('es-ES', { weekday: 'short', day: '2-digit' }));
    return daysString;
  };
export default Dias;